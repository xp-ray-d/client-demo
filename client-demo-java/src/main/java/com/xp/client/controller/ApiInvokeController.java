package com.xp.client.controller;

import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.v2.ApiNotifyReq;
import com.xp.client.dto.v2.ApiNotifyRsp;
import com.xp.client.dto.v2.body.ApiBodyNotifyReq;
import com.xp.client.dto.v2.body.ApiBodyNotifyRsp;
import com.xp.client.dto.v2.body.MsgPublicNotifyRsp;
import com.xp.client.enums.EncryptKeyTypeEnum;
import com.xp.client.utils.SecurityUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiInvokeController {

    /**
     * @desc: 接收支付通知
     * @version: 1.0
     */
    @PostMapping(value = "/txn/v2/simulator/pay/notify")
    @ResponseBody
    public ApiNotifyRsp payNotify(final HttpServletRequest request, @RequestBody final String body) {
        log.info(body);
        {
            final ApiNotifyReq apiNotifyReq = JSONUtil.toBean(body, ApiNotifyReq.class);
            final boolean flag = apiNotifyReq.verifySign(
                    SecurityUtil.getKeystore(ClientConfig.getDefault().getListKeyDto(), EncryptKeyTypeEnum.ASYM_PUBLIC_PLAT), apiNotifyReq.getBody(),
                    apiNotifyReq.getSign());
            Assert.isTrue(flag, "验签失败");

            final ApiBodyNotifyReq apiBodyNotifyReq = JSONUtil.toBean(apiNotifyReq.getBody(), ApiBodyNotifyReq.class);
            log.info(JSONUtil.toJsonStr(apiBodyNotifyReq));
            // TODO 业务处理
        }

        final MsgPublicNotifyRsp msgPublic = new MsgPublicNotifyRsp();
        msgPublic.setRspCode("0000");
        final ApiBodyNotifyRsp msgBody = new ApiBodyNotifyRsp();
        msgBody.setMsgPublic(msgPublic);
        final ApiNotifyRsp rsp = new ApiNotifyRsp();
        rsp.setBody(msgBody);
        return rsp;
    }
}
