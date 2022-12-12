package com.xp.client.service;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.config.ClientConfig;
import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.dto.v2.ApiReqExt;
import com.xp.client.dto.v2.ApiRsp;
import com.xp.client.dto.v2.ApiRspExt;
import com.xp.client.dto.v2.body.ApiBodyReqExt;
import com.xp.client.dto.v2.body.ApiBodyRspExt;
import com.xp.client.dto.v2.body.MsgProtectedExt;
import com.xp.client.dto.v2.body.MsgPublicReq;
import com.xp.client.dto.v2.body.SecretKey;
import com.xp.client.enums.EncryptKeyTypeEnum;
import com.xp.client.utils.DateTimeUtil;
import com.xp.client.utils.IDUtil;
import com.xp.client.utils.SecurityUtil;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

/**
 * @desc: 公共处理类
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class BaseApiInvokeService {

    /**
     * @desc: 组装请求报文，签名
     */
    public static ApiReqExt buildReq(final ClientConfig client,
                                     final JSONObject jsonPrivate) {
        return BaseApiInvokeService.buildReq(client, jsonPrivate, null, null);
    }

    public static ApiReqExt buildReq(final ClientConfig client,
                                     final JSONObject jsonPrivate,
                                     final JSONObject jsonProtected,
                                     final SecretKey secretKey) {
        final List<XpKeystoreDto> listKeyDto = client.getListKeyDto();

        // 公共报文体(测试时可使用默认参数，实际生产环境以传入的参数为主)
        final MsgPublicReq msgPublicReq = MsgPublicReq.builder()
                .version("2.0")
                .cusReqTime(DateTimeUtil.getDateTimeYMDHMS())
                .cusTraceNo(IDUtil.generateShortUUID())
                .cusCode(client.getCusCode())
                .build();

        // 请求报文体
        final ApiBodyReqExt apiBodyReqExt = new ApiBodyReqExt();
        apiBodyReqExt.setMsgPublic(msgPublicReq);
        apiBodyReqExt.setMsgPrivate(jsonPrivate);

        // 保护报文体
        if (jsonProtected != null) {
            final MsgProtectedExt msgProtectedExt = new MsgProtectedExt();
            msgProtectedExt.setJsonProtected(jsonProtected);

            apiBodyReqExt.setMsgProtectedExt(msgProtectedExt);
            apiBodyReqExt.setSecretKey(secretKey);
            apiBodyReqExt.encrypt(SecurityUtil.getKeystore(listKeyDto, EncryptKeyTypeEnum.ASYM_PUBLIC_PLAT), null);
        }

        // 请求报文
        final ApiReqExt apiReqExt = new ApiReqExt();
        apiReqExt.setObjBody(apiBodyReqExt);
        apiReqExt.setBody(JSONUtil.toJsonStr(apiReqExt.getObjBody().getApiBodyReq()));

        // 签名
        apiReqExt.setSign(apiReqExt.sign(SecurityUtil.getKeystore(listKeyDto, EncryptKeyTypeEnum.ASYM_PRIVATE), apiReqExt.getBody()));
        return apiReqExt;
    }

    public static ApiRsp invoke(final ClientConfig client,
                                final String uri,
                                final ApiReqExt apiReqExt) {
        // 请求
        final String rsp = HttpRequest.post(client.getBaseUrl().concat(uri))
                .header(Header.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .body(JSONUtil.toJsonStr(apiReqExt.getApiReq()))
                .execute().body();
        log.info(rsp);

        // 应答
        final ApiRspExt apiRspExt = getRsp(client, rsp);
        return apiRspExt;
    }

    protected static ApiRspExt getRsp(final ClientConfig client,
                                      final String rsp) {
        final List<XpKeystoreDto> listKeyDto = client.getListKeyDto();

        // 应答报文
        final ApiRspExt apiRspExt = JSONUtil.toBean(rsp, ApiRspExt.class);
        final boolean flag = apiRspExt.verifySign(SecurityUtil.getKeystore(listKeyDto, EncryptKeyTypeEnum.ASYM_PUBLIC_PLAT),
                                                  apiRspExt.getBody(),
                                                  apiRspExt.getSign());
        Assert.isTrue(flag, "验签失败");

        final ApiBodyRspExt apiBodyRspExt = JSONUtil.toBean(apiRspExt.getBody(), ApiBodyRspExt.class);
        apiRspExt.setObjBody(apiBodyRspExt);
        apiBodyRspExt.decrypt(SecurityUtil.getKeystore(listKeyDto, EncryptKeyTypeEnum.ASYM_PRIVATE), null);
        return apiRspExt;
    }
}
