package com.xp.client.pay.wxpay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.service.BaseApiInvokeService;
import com.xp.client.utils.DateTimeUtil;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 微信支付-WAP(H5)支付
 */
public class PayWap {

    public static void main(String[] args) throws Exception {

        final JSONObject bsnInfo = new JSONObject();
        bsnInfo.putIfAbsent("subject", "WAP(H5)支付测试");
        bsnInfo.putIfAbsent("expire", DateTimeUtil.format(DateUtils.addHours(new Date(), 1), DatePattern.PURE_DATETIME_PATTERN));

        final JSONObject scene = new JSONObject();
        scene.putIfAbsent("type", "Wap"); // 场景类型
        scene.putIfAbsent("ip", "120.78.159.245");

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("txnAmt", "1"); // 金额(分)
        msgPrivate.putIfAbsent("txnRemark", "订单附言");
        msgPrivate.putIfAbsent("memo", "回传信息");
        msgPrivate.putIfAbsent("notifyUrl", "http://127.0.0.1:8078/txn/v2/simulator/pay/notify");
        msgPrivate.putIfAbsent("bsnInfo", bsnInfo);
        msgPrivate.putIfAbsent("scene", scene);

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/wxpay/pay/wap", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
