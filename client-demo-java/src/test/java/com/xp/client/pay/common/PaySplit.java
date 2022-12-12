package com.xp.client.pay.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.dto.v2.body.SplitReceiver;
import com.xp.client.enums.SplitReceiverEnum;
import com.xp.client.service.BaseApiInvokeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: gzb
 * @date: 20221027
 * @desc: 发起分账
 * @version: 1.0
 */
public class PaySplit {

    public static void main(String[] args) throws Exception {

        // 分账信息
        final List<SplitReceiver> listReceiver = new ArrayList<>();
        final SplitReceiver receiver = new SplitReceiver();
        receiver.setType(SplitReceiverEnum.ALIPAY_USER_ID.getCode()); // 支付宝用户id
        receiver.setAcct(""); // type=alipayUserId：2088开头的16位数字
        receiver.setName("");
        receiver.setAmt(BigDecimal.ONE); // 金额(分)
        listReceiver.add(receiver);

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("origTxnDate", "20221025");
        msgPrivate.putIfAbsent("origSysTraceNo", "20221025782238042493583360");
        msgPrivate.putIfAbsent("receiver", listReceiver);

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/pay/split", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
