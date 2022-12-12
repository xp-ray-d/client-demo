package com.xp.client.utils;

import com.xp.client.enums.EncryptAlgorithmEnum;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;

public class AsymmetricEncryptUtil {

    public static String encrypt(final String data, final Charset charset, final String encryptAlgorithm, final String publicKey) {
        String params = String.format("[encryptAlgorithm: %s][charset: %s]", encryptAlgorithm, charset.name());
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(String.format("[%s]加密失败：明文为空", params));
        } else if (StringUtils.isBlank(publicKey)) {
            throw new RuntimeException(String.format("[%s]加密失败：密钥为空", params));
        } else if (StringUtils.isBlank(encryptAlgorithm)) {
            throw new RuntimeException(String.format("[%s]加密失败：加密算法为空", params));
        } else if (StringUtils.equals(encryptAlgorithm, EncryptAlgorithmEnum.ASYM_RSA.getValue())) {
            return XpRSAUtil.encryptBase64ByPublicKey(data, charset, publicKey);
        } else if (StringUtils.equals(encryptAlgorithm, EncryptAlgorithmEnum.ASYM_SM2.getValue())) {
            return XpSM2Util.encryptBase64ByPublicKey(data, charset, publicKey);
        } else {
            throw new RuntimeException(String.format("[%s]加密失败：加密算法[%s]不支持", params, encryptAlgorithm));
        }
    }

    public static String decrypt(final String data, final String encryptAlgorithm, final String privateKey, final Charset charset) {
        String params = String.format("[encryptAlgorithm: %s][charset: %s]", encryptAlgorithm, charset.name());
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(String.format("[%s]解密失败：密文为空", params));
        } else if (StringUtils.isBlank(privateKey)) {
            throw new RuntimeException(String.format("[%s]解密失败：密钥为空", params));
        } else if (StringUtils.isBlank(encryptAlgorithm)) {
            throw new RuntimeException(String.format("[%s]解密失败：加密算法为空", params));
        } else if (StringUtils.equals(encryptAlgorithm, EncryptAlgorithmEnum.ASYM_RSA.getValue())) {
            return XpRSAUtil.decryptBase64ByPrivateKey(data, charset, privateKey);
        } else if (StringUtils.equals(encryptAlgorithm, EncryptAlgorithmEnum.ASYM_SM2.getValue())) {
            return XpSM2Util.decryptBase64ByPrivateKey(data, charset, privateKey);
        } else {
            throw new RuntimeException(String.format("[%s]解密失败：加密算法[%s]不支持", params, encryptAlgorithm));
        }
    }
}
