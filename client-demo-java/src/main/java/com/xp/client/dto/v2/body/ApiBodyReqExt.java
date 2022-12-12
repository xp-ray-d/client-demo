package com.xp.client.dto.v2.body;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:44
 * @Description: API请求报文体（扩展：方便加解密操作）
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiBodyReqExt extends ApiBodyReq implements Serializable {

    protected MsgProtectedExt msgProtectedExt;

    /**
     * 分账参数
     */
    @JsonIgnore
    private List<SplitReceiver> listReceiver;

    public String toJsonStrSorted() {
        return "";
    }

    public String toJsonStrHashed() {
        return "";
    }

    public ApiBodyReq getApiBodyReq() {
        final ApiBodyReq apiBodyReq = new ApiBodyReq();
        apiBodyReq.setMsgPublic(this.getMsgPublic());
        apiBodyReq.setMsgPrivate(this.getMsgPrivate());
        if (msgProtectedExt != null && StringUtils.isNotBlank(msgProtectedExt.getEncrypted())) {
            apiBodyReq.setMsgProtected(msgProtectedExt.getEncrypted());
        }
        apiBodyReq.setSecretKey(this.getSecretKey());
        return apiBodyReq;
    }

    public void encrypt(final XpKeystoreDto publicKey, final XpKeystoreDto symKey) {
        if (msgProtectedExt == null || msgProtectedExt.getJsonProtected() == null) {
            return;
        }
        if (this.getSecretKey() == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[msgProtected]不为空，[secretKey]为空");
        }
        if (publicKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "[secretKey]加密公钥对象为空");
        }

        // 加密报文
        final SecretKeyExt secretKeyExt = new SecretKeyExt();
        final String encrypted = msgProtectedExt.encrypt(symKey, secretKeyExt);
        this.setMsgProtected(encrypted);

        // 加密对称密钥
        secretKeyExt.encrypt(publicKey);
        if (this.getSecretKey() == null) {
            this.secretKey = new SecretKey();
        }
        this.getSecretKey().setEncryptKey(secretKeyExt.getEncryptKey());
        this.getSecretKey().setEncryptIV(secretKeyExt.getEncryptIV());
    }

    public void decrypt(final XpKeystoreDto privateKey, final XpKeystoreDto symKey) {
        if (StringUtils.isBlank(this.getMsgProtected())) {
            return;
        }
        if (this.getSecretKey() == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[msgProtected]不为空，[secretKey]为空");
        }
        if (privateKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "[secretKey]解密私钥对象为空");
        }

        // 解密对称密钥
        final SecretKeyExt secretKeyExt = new SecretKeyExt(secretKey);
        secretKeyExt.decrypt(privateKey);

        // 解密报文
        this.msgProtectedExt = new MsgProtectedExt();
        this.msgProtectedExt.setEncrypted(this.getMsgProtected());
        this.msgProtectedExt.decrypt(symKey, secretKeyExt);
    }
}
