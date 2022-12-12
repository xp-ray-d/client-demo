package com.xp.client.cus;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.enums.SplitReceiverEnum;
import com.xp.client.service.BaseApiInvokeService;

/**
 * 分账方绑定查询
 *
 * @author: gzb
 * @date: 20221027
 * @desc:
 * @version: 1.0
 */
public class SplitBindQuery {

    public static void main(String[] args) throws Exception {

        // 私有参数体
        final JSONObject msgPrivate = new JSONObject();
        msgPrivate.putIfAbsent("type", SplitReceiverEnum.ALIPAY_LOGIN_NAME.getCode()); // 支付宝用户登录名
        msgPrivate.putIfAbsent("acct", "yyq@kj-pay.com"); // type=alipayLoginName：手机号/邮箱等

        // 组装请求报文
        final ClientConfig clientConfig = ClientConfig.getDefault();
        final ApiReqExt apiReqExt = BaseApiInvokeService.buildReq(clientConfig, msgPrivate);

        // 发送 http 请求
        final ApiRsp apiRsp = BaseApiInvokeService.invoke(clientConfig, "/txn/v2/alipay/split/bind/query", apiReqExt);
        System.out.println(JSONUtil.toJsonStr(apiRsp));
    }
}
