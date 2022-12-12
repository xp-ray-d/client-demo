package com.xp.client.dto.v2.body;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:44
 * @Description: API应答报文体（扩展：方便加解密操作）
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiBodyRspExt extends ApiBodyRsp implements Serializable {

    protected MsgProtectedExt msgProtectedExt;

    /**
     * 接收统一交易的响应参数
     */
    @Deprecated
    @JsonIgnore
    protected JSONObject rspJson;

    public void decrypt(final XpKeystoreDto privateKey, final XpKeystoreDto symKey) {
        if (StringUtils.isBlank(this.getMsgProtected())) {
            return;
        }
        if (this.getSecretKey() == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "应答报文[msgProtected]不为空，[secretKey]为空");
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
