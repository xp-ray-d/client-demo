package com.xp.client.pay.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.service.BaseApiInvokeService;

/**
 * @Author: 时雨
 * @CreateTime: 2022-11-03  15:57
 * @Description: 交易撤销
 * @Version: 1.0
 */
public class Reverse {

    public static void main(String[] args) throws Exception {

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("origTxnDate", "20221103");
        msgPrivate.putIfAbsent("origSysTraceNo", "20221103**********20608");

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/reverse", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
