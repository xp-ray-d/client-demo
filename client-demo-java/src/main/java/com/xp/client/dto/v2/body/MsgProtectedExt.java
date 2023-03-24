package com.xp.client.dto.v2.body;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.enums.*;
import com.xp.client.utils.BaseException;
import com.xp.client.utils.SymmetricEncryptUtil;
import com.xp.client.utils.SymmetricKeyUtil;
import com.xp.client.utils.XpEncodeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:43
 * @Description: 保护报文体（扩展，方便加解密操作）
 * @Version: 1.0
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgProtectedExt {

    protected JSONObject jsonProtected;
    //protected String plainJson;
    protected String encrypted;

    public static XpKeystoreDto genDefaultKeystore() {
        final XpKeystoreDto symKey = new XpKeystoreDto();
        symKey.setCharset(StandardCharsets.UTF_8.name());
        symKey.setKeyAlgorithmEncode(EncodeTypeEnum.BASE64.getValue());
        symKey.setKeyAlgorithmEncrypt(EncryptAlgorithmEnum.SYM_AES.getValue());
        symKey.setKeyMode(EncryptModeEnum.ECB.getValue());
        symKey.setKeyPadding(EncryptPaddingEnum.PKCS5Padding.getValue());
        return symKey;
    }

    public String encrypt(XpKeystoreDto keystore, final SecretKeyExt secretKey) {
        if (jsonProtected == null) {
            log.error("保护报文体为空");
            return "";
        }
        final String plain = jsonProtected.toString();
        if (StringUtils.isBlank(plain)) {
            log.error("待加密字符串为空");
            return "";
        }
        if (!JSONUtil.isTypeJSONObject(plain)) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_PARAM, "[MsgProtectedExt]加密失败：待加密报文体不符合json格式");
        }
        if (secretKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "[MsgProtectedExt]加密失败：报文安全密钥[secretKey]为空");
        }
        if (keystore == null) {
            keystore = genDefaultKeystore();
        }
        if (StringUtils.isBlank(secretKey.getEncryptKeyPlain())) {
            secretKey.setEncryptKeyPlain(
                    StringUtils.isBlank(keystore.getKeyValue()) ? SymmetricKeyUtil.getSymmetricKey(keystore.getKeyAlgorithmEncrypt())
                            : keystore.getKeyValue());
        }
        if (StringUtils.isBlank(secretKey.getEncryptIVPlain())) {
            secretKey.setEncryptIVPlain(StringUtils.isBlank(keystore.getKeyIv()) ? SymmetricKeyUtil.getSymmetricIv(keystore.getKeyAlgorithmEncrypt())
                                                : keystore.getKeyIv());
        }

        try {
            final String cipher = SymmetricEncryptUtil.encrypt(
                    plain,
                    StringUtils.isBlank(keystore.getCharset()) ? StandardCharsets.UTF_8 : Charset.forName(keystore.getCharset()),
                    StringUtils.isBlank(keystore.getKeyAlgorithmEncrypt()) ? EncryptAlgorithmEnum.SYM_AES
                            : EncryptAlgorithmEnum.findEnum(keystore.getKeyAlgorithmEncrypt()),
                    StringUtils.isBlank(keystore.getKeyMode()) ? EncryptModeEnum.ECB : EncryptModeEnum.findEnum(keystore.getKeyMode()),
                    StringUtils.isBlank(keystore.getKeyPadding()) ? EncryptPaddingEnum.PKCS5Padding
                            : EncryptPaddingEnum.findEnum(keystore.getKeyPadding()),
                    XpEncodeUtil.encodeBase64(secretKey.getEncryptKeyPlain()),
                    XpEncodeUtil.encodeBase64(secretKey.getEncryptIVPlain()),
                    StringUtils.isBlank(keystore.getKeyAlgorithmEncode()) ? EncodeTypeEnum.BASE64
                            : EncodeTypeEnum.findEnum(keystore.getKeyAlgorithmEncode()));
            this.setEncrypted(cipher);
            return cipher;
        } catch (Exception e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS, MessageFormat.format("[MsgProtectedExt][{0}]加密失败：{1}", plain, e.getMessage()));
        }
    }

    public String decrypt(XpKeystoreDto keystore, final SecretKeyExt secretKey) {
        final String cipher = this.getEncrypted();
        if (StringUtils.isBlank(cipher)) {
            log.error("待解密字符串为空");
            return "";
        }
        if (secretKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "[MsgProtectedExt]解密失败：报文安全密钥[secretKey]为空");
        }
        if (keystore == null) {
            keystore = genDefaultKeystore();
        }

        try {
            String encryptKey = StringUtils.isBlank(secretKey.getEncryptKeyPlain()) ? keystore.getKeyValue() : secretKey.getEncryptKeyPlain();
            String encryptIV = StringUtils.isBlank(secretKey.getEncryptIVPlain()) ? keystore.getKeyIv() : secretKey.getEncryptIVPlain();
            final String plain = SymmetricEncryptUtil.decrypt(cipher,
                                                              StringUtils.isBlank(keystore.getKeyAlgorithmEncode()) ? EncodeTypeEnum.BASE64
                                                                      : EncodeTypeEnum.findEnum(keystore.getKeyAlgorithmEncode()),
                                                              StringUtils.isBlank(keystore.getKeyAlgorithmEncrypt()) ? EncryptAlgorithmEnum.SYM_AES
                                                                      : EncryptAlgorithmEnum.findEnum(keystore.getKeyAlgorithmEncrypt()),
                                                              StringUtils.isBlank(keystore.getKeyMode()) ? EncryptModeEnum.ECB
                                                                      : EncryptModeEnum.findEnum(keystore.getKeyMode()),
                                                              StringUtils.isBlank(keystore.getKeyPadding()) ? EncryptPaddingEnum.PKCS5Padding
                                                                      : EncryptPaddingEnum.findEnum(keystore.getKeyPadding()),
                                                              StringUtils.isBlank(encryptKey) ? encryptKey : XpEncodeUtil.encodeBase64(encryptKey),
                                                              StringUtils.isBlank(encryptIV) ? encryptIV : XpEncodeUtil.encodeBase64(encryptIV),
                                                              StringUtils.isBlank(keystore.getCharset()) ? StandardCharsets.UTF_8
                                                                      : Charset.forName(keystore.getCharset()));
            if (JSONUtil.isTypeJSONObject(plain)) {
                this.setJsonProtected(JSONUtil.parseObj(plain));
            } else {
                throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT,
                                        MessageFormat.format("[MsgProtectedExt]报文体解密后明文格式非JSON：{0}", plain));
            }
            return plain;
        } catch (Exception e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS,
                                    MessageFormat.format("[MsgProtectedExt][{0}]报文体解密失败：{1}", cipher, e.getMessage()));
        }
    }
}
