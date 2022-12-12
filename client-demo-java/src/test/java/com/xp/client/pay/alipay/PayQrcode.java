package com.xp.client.pay.alipay;

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
 * @Author: 时雨
 * @CreateTime: 2022-11-03  15:49
 * @Description: 支付宝-付款码支付（被扫）
 * @Version: 1.0
 */
public class PayQrcode {

    public static void main(String[] args) throws Exception {

        final JSONObject bsnInfo = new JSONObject();
        bsnInfo.putIfAbsent("subject", "测试支付");
        bsnInfo.putIfAbsent("expire", DateTimeUtil.format(DateUtils.addHours(new Date(), 1), DatePattern.PURE_DATETIME_PATTERN));

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("txnAmt", "1"); // 金额(分)
        msgPrivate.putIfAbsent("txnRemark", "订单附言");
        msgPrivate.putIfAbsent("memo", "回传信息");
        msgPrivate.putIfAbsent("authCode", "289125*********37990"); // 支付授权码
        msgPrivate.putIfAbsent("bsnInfo", bsnInfo);

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/alipay/pay/qrcode", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
