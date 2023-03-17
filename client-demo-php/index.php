<?php

    header("Content-type:text/html;charset=utf-8");

    $type = $_GET["type"];

    //平台分配给客户的唯一识别号
    $cusCode = "C0000035C0001";
    //平台公钥
    $platPublicKey = file_get_contents("public.key");
    //客户私钥
    $cusPrivateKey = file_get_contents("C0000035C0001_PKCS1.key");

    //公共参数体
    $msgPublic["version"]    = "2.0";
    $msgPublic["cusReqTime"] = date("YmdHis");
    $msgPublic["cusTraceNo"] = md5(uniqid(mt_rand(), true));
    $msgPublic["cusCode"]    = $cusCode;

    file_put_contents(date("Y-m-d") . ".txt", $msgPublic["cusTraceNo"]);

    //私有参数体
    $msgPrivate["origTxnDate"]    = date("Ymd");//支付订单日期
    $msgPrivate["origCusTraceNo"] = "afcc86f2bd7d475024e6d5c95e356402";//支付订单的客户订单号
    $msgPrivate["origSysTraceNo"] = "";

    $msgPrivate2["txnAmt"]             = "1";
    $msgPrivate2["memo"]               = "订单的自定义数据";
    $msgPrivate2["bsnInfo"]["subject"] = "支付标题";
    $msgPrivate2["bsnInfo"]["expire"]  = "20221122113013";
    $msgPrivate2["sett"]               = "1";
    $msgPrivate2["split"]              = "2";
    $msgPrivate2["notifyUrl"]          = "http://192.168.20.104:48079/txn/v2/simulator/alipayPayWap/notify";

    //报文体
    $body["msgPublic"]  = $msgPublic;

    switch ($type) {
        case "pay"://扫码支付(主扫)
            $body["msgPrivate"] = $msgPrivate2;
            $url = "https://api.kuaijie-pay.com/forward/pay/txn/v2/wxpay/pay/native";
            break;

        case "query"://支付结果查询
            $body["msgPrivate"] = $msgPrivate;
            $url = "https://api.kuaijie-pay.com/forward/pay/txn/v2/pay/query";
            break;
        default:
            die("参数错误");

    }

    //数字签名，防止json加反斜杠和中文编码，否则验签失败
    //报文体需要转成json再进行签名
    $sign = getRsaSign(json_encode($body, JSON_UNESCAPED_SLASHES|JSON_UNESCAPED_UNICODE), $cusPrivateKey);

    //组装接口请求数据
    $data = [
        "body" => $body,
        "sign" => $sign,
    ];

    //请求接口
    $result = curl_request($url, $data);

    //获取返回的签名
    $return_sign = $result["sign"];

    //校验签名，注意$result["body"]已是字符串，无需再json编码
    $verifySign = verifySign($result["body"], $return_sign, $platPublicKey);
    if (!$verifySign) {//验签失败
        die("验签失败");
    }

    //对结果body层做json解码处理
    $result["body"] = json_decode($result["body"], true);

    die(json_encode($result));

    /**
     * 校验签名
     * @param $content   [接口返回的body的键值字符串]
     * @param $sign      [接口返回的签名]
     * @param $publicKey [平台公钥]
     * @return bool
     * @author sensus
     */
    function verifySign($content, $sign, $publicKey)
    {
        $sign      = base64_decode($sign);
        $publicKey = "-----BEGIN PUBLIC KEY-----\n" .
            wordwrap($publicKey, 64, "\n", true) .
            "\n-----END PUBLIC KEY-----";

        $pubkeyid  = openssl_get_publickey($publicKey);

        if ($pubkeyid) {
            //返回值：如果签名正确则返回1，如果不正确则返回0，错误时返回-1或false。
            $verify = openssl_verify($content, $sign, $pubkeyid, "sha256");
            openssl_free_key($pubkeyid);
            if ($verify == 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取签名
     * @param $content
     * @param $privateKey
     * @return string
     * @author sensus
     */
    function getRsaSign($content, $privateKey)
    {
        $privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" .
            wordwrap($privateKey, 64, "\n", true) .
            "\n-----END RSA PRIVATE KEY-----";

        $key = openssl_get_privatekey($privateKey);
        openssl_sign($content, $signature, $key, "SHA256");
        openssl_free_key($key);
        $sign = base64_encode($signature);

        return $sign;
    }

    /**
     * curl请求
     * @param string $url
     * @param $sendData
     * @param int $jsonType
     * @param array $headerArray
     * @return mixed|string
     * @author sensus
     */
    function curl_request($url, $sendData, $jsonType = 1, $headerArray = [])
    {
        if ($jsonType) {
            $sendData    = json_encode($sendData);
            $headerArray = ["Content-type:application/json;charset=UTF-8"];
        }
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, FALSE);
        curl_setopt($curl, CURLOPT_POST, 1);
        curl_setopt($curl, CURLOPT_POSTFIELDS, $sendData);
        curl_setopt($curl, CURLOPT_HTTPHEADER, $headerArray);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_CONNECTTIMEOUT, 10);
        curl_setopt($curl, CURLOPT_TIMEOUT, 10);
        $output = curl_exec($curl);
        if ($output) {
            curl_close($curl);
            $output = str_replace("\\\\\\", "\\", $output);//处理字符串中的多层反斜杠
            return json_decode($output, true);
        } else {
            $error = 'Curl error: ' . curl_error($curl);
            curl_close($curl);
            return $error;
        }
    }

    /**
     * 断点调试
     * @param $data
     * @author sensus
     */
    function dd($data)
    {
        var_dump($data);
        die;
    }