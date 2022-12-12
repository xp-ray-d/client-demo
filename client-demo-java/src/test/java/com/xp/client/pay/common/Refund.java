package com.xp.client.pay.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.service.BaseApiInvokeService;

/**
 * @author: gzb
 * @date: 20221027
 * @desc: 退款
 * @version: 1.0
 */
public class Refund {

    public static void main(String[] args) throws Exception {

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("origTxnDate", "20221212");
        msgPrivate.putIfAbsent("origSysTraceNo", "20221212799719848982683648");
        msgPrivate.putIfAbsent("txnAmt", "1"); // 金额(分)

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/refund", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
