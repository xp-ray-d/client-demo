package com.xp.client.pay.cup;

import cn.hutool.core.date.DatePattern;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.dto.v2.body.SecretKey;
import com.xp.client.service.BaseApiInvokeService;
import com.xp.client.utils.DateTimeUtil;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 协议支付-签约(绑卡)并下单
 */
public class CupPayQuickSign {

    public static void main(String[] args) throws Exception {

        final JSONObject bsnInfo = new JSONObject();
        bsnInfo.putIfAbsent("subject", "协议支付-签约(绑卡)并下单测试");
        bsnInfo.putIfAbsent("expire", DateTimeUtil.format(DateUtils.addHours(new Date(), 1), DatePattern.PURE_DATETIME_PATTERN));

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("txnAmt", "1"); // 金额(分)
        msgPrivate.putIfAbsent("txnRemark", "订单附言");
        msgPrivate.putIfAbsent("memo", "回传信息");
        msgPrivate.putIfAbsent("notifyUrl", "http://127.0.0.1:8078/txn/v2/simulator/pay/notify");
        msgPrivate.putIfAbsent("bsnInfo", bsnInfo);

        // 保护参数体
        final JSONObject msgProtected = new JSONObject();
        msgProtected.putIfAbsent("acctName", "张三");
        msgProtected.putIfAbsent("acctNo", "1234567890");
        msgProtected.putIfAbsent("mobile", "15012344321");
        msgProtected.putIfAbsent("certNo", "3502134241241424");

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate, msgProtected);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/cup/pay/quick/sign", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
