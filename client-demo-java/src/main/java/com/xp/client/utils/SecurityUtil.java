package com.xp.client.utils;

import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.enums.EncryptKeyTypeEnum;
import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.enums.SignAlgorithmEnum;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

@Slf4j
public class SecurityUtil {

    public static XpKeystoreDto getKeystore(final List<XpKeystoreDto> listKeystore, final EncryptKeyTypeEnum keyTypeEnum) {
        if (listKeystore == null || CollectionUtils.isEmpty(listKeystore)) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "密钥配置为空");
        }
        final List<XpKeystoreDto> list = listKeystore.stream()
                .filter(key -> StringUtils.equals(StringUtils.trimToEmpty(key.getKeyType()), keyTypeEnum.getValue()))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(list)) {
            if (StringUtils.equals(keyTypeEnum.getValue(), EncryptKeyTypeEnum.SYM.getValue())) {
                return null;
            } else {
                throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG,
                                        MessageFormat.format("[{0}]{1}配置为空", listKeystore.get(0).getObjCode(), keyTypeEnum.getDesc()));
            }
        } else if (list.size() > 1) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG,
                                    MessageFormat.format("[{0}]{1}配置数量 > 1", listKeystore.get(0).getObjCode(), keyTypeEnum.getDesc()));
        }
        return list.get(0);
    }

    public static String sign(final String bodyJson, final XpKeystoreDto keystore) {
        if (StringUtils.isBlank(bodyJson)) {
            log.error("待签名字符串为空");
            return "";
        } else if (keystore == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "签名失败：密钥对象为空");
        }

        final String privateKey = AsymmetricKeyUtil.getPrivateKeyStr(keystore);
        if (privateKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "签名失败：读取私钥为空");
        }
        try {
            final String strSign = XpSignUtil.sign(
                    bodyJson,
                    StringUtils.isBlank(keystore.getCharset()) ? StandardCharsets.UTF_8 : Charset.forName(keystore.getCharset()),
                    StringUtils.isBlank(keystore.getKeyAlgorithmSign()) ? SignAlgorithmEnum.SHA256withRSA.getValue() : keystore.getKeyAlgorithmSign(),
                    privateKey);
            return strSign;
        } catch (Exception e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS, MessageFormat.format("签名失败：{0}", e.getMessage()));
        }
    }

    public static boolean verifySign(final String bodyJson, final String sign, final XpKeystoreDto keystore) {
        if (StringUtils.isBlank(bodyJson)) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "待验签字符串为空");
        } else if (StringUtils.isBlank(sign)) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "签名字符串为空");
        } else if (keystore == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "签名失败：密钥对象为空");
        }

        final String publicKey = AsymmetricKeyUtil.getPublicKeyStr(keystore);
        if (publicKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "验签失败：读取公钥为空");
        }
        try {
            return XpSignUtil.verify(bodyJson,
                                     StringUtils.isBlank(keystore.getCharset()) ? StandardCharsets.UTF_8 : Charset.forName(keystore.getCharset()),
                                     StringUtils.isBlank(keystore.getKeyAlgorithmSign()) ? SignAlgorithmEnum.SHA256withRSA.getValue()
                                             : keystore.getKeyAlgorithmSign(),
                                     publicKey,
                                     sign);
        } catch (Exception e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS, MessageFormat.format("验签失败：{0}", e.getMessage()));
        }
    }

    public static String encrypt(final String plain, final XpKeystoreDto keystore, final String encryptAlgorithm) {
        if (StringUtils.isBlank(plain)) {
            log.error("待加密字符串为空");
            return "";
        } else if (keystore == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "安全密钥加密失败：密钥对象为空");
        }

        final String publicKey = AsymmetricKeyUtil.getPublicKeyStr(keystore);
        if (publicKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "安全密钥加密失败：读取公钥为空");
        }
        try {
            final String cipher = AsymmetricEncryptUtil.encrypt(plain,
                                                                StringUtils.isBlank(keystore.getCharset()) ? StandardCharsets.UTF_8
                                                                        : Charset.forName(keystore.getCharset()),
                                                                StringUtils.isBlank(encryptAlgorithm) ? keystore.getKeyAlgorithmEncrypt()
                                                                        : encryptAlgorithm,
                                                                publicKey);
            return cipher;
        } catch (Exception e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS, MessageFormat.format("安全密钥[0]加密失败：{1}", plain, e.getMessage()));
        }
    }

    public static String decrypt(final String cipher, final XpKeystoreDto keystore, final String encryptAlgorithm) {
        if (StringUtils.isBlank(cipher)) {
            log.error("待解密字符串为空");
            return "";
        } else if (keystore == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, "安全密钥解密失败：密钥对象为空");
        }

        final String privateKey = AsymmetricKeyUtil.getPrivateKeyStr(keystore);
        if (privateKey == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "安全密钥解密失败：读取私钥为空");
        }
        try {
            final String plain = AsymmetricEncryptUtil.decrypt(cipher,
                                                               StringUtils.isBlank(encryptAlgorithm) ? keystore.getKeyAlgorithmEncrypt()
                                                                       : encryptAlgorithm,
                                                               privateKey,
                                                               StringUtils.isBlank(keystore.getCharset()) ? StandardCharsets.UTF_8
                                                                       : Charset.forName(keystore.getCharset()));
            return plain;
        } catch (Exception e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS, MessageFormat.format("安全密钥[0]解密失败：{1}", cipher, e.getMessage()));
        }
    }
}
