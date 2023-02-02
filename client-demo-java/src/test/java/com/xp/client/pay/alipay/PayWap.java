package com.xp.client.pay.alipay;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.service.BaseApiInvokeService;

/**
 * @author: gzb
 * @date: 20221027
 * @desc: 支付宝WAP支付
 * @version: 1.0
 */
public class PayWap {

    public static void main(String[] args) throws Exception {

        final JSONObject bsnInfo = new JSONObject();
        bsnInfo.putIfAbsent("subject", "测试支付");
        //bsnInfo.putIfAbsent("expire", DateTimeUtil.format(DateUtils.addHours(new Date(), 1), DatePattern.PURE_DATETIME_PATTERN));

        final JSONObject scene = new JSONObject();
        scene.putIfAbsent("ip", "180.89.106.144");
        scene.putIfAbsent("type", "Wap");

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("txnAmt", 1); // 金额(分)
        msgPrivate.putIfAbsent("currency", "CNY");
        msgPrivate.putIfAbsent("bsnInfo", bsnInfo);
        msgPrivate.putIfAbsent("scene", scene);
        msgPrivate.putIfAbsent("txnRemark", "订单附言");
        msgPrivate.putIfAbsent("memo", "回传信息");
        msgPrivate.putIfAbsent("notifyUrl", "http://127.0.0.1:8078/txn/v2/simulator/pay/notify");

        // msgPrivate.putIfAbsent(ApiMsgFieldEnum.SETT.getCode(), SettModeEnum.SETT_AUTO.getValue());
        // msgPrivate.putIfAbsent(ApiMsgFieldEnum.SPLIT.getCode(), SplitModeEnum.SPLIT_ONCE.getValue());
        // final SplitReceiver splitReceiver = new SplitReceiver();
        // splitReceiver.setType(SplitReceiverEnum.ALIPAY_LOGIN_NAME.getCode());
        // splitReceiver.setAcct("yyq@kj-pay.com");
        // splitReceiver.setName("厦门快接网络科技有限公司");
        // splitReceiver.setMemo("分账测试");
        // splitReceiver.setAmt(BigDecimal.valueOf(1));
        // final List<SplitReceiver> listReceiver = Arrays.asList(splitReceiver);
        // msgPrivate.set(ApiMsgFieldEnum.RECEIVER.getCode(), listReceiver);

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/alipay/pay/wap", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
