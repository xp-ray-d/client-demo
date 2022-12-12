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
 * @desc: 支付宝支付查询
 * @version: 1.0
 */
public class PayQuery {

    public static void main(String[] args) throws Exception {

        final JSONObject msgPrivate = new JSONObject(); //私有参数体
        msgPrivate.putIfAbsent("origTxnDate", "20221025");
        msgPrivate.putIfAbsent("origSysTraceNo", "20221025782238042493583360"); // 不可与origCusTraceNo同时为空

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/pay/query", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
