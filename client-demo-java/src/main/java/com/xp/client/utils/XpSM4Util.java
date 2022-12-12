package com.xp.client.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SM4;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.EncryptModeEnum;
import com.xp.client.enums.EncryptPaddingEnum;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XpSM4Util {

    public XpSM4Util() {
    }

    public static String encryptHexWithECB(final String data, final Charset charset, final String key) {
        SM4 algorithm = new SM4(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        return algorithm.encryptHex(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptHexWithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithECB(data, charset, key, padding);
        return XpEncodeUtil.encodeHex(encrypted);
    }

    public static String encryptBase64WithECB(final String data, final Charset charset, final String key) {
        SM4 algorithm = new SM4(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        return algorithm.encryptBase64(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptBase64WithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithECB(data, charset, key, padding);
        return XpEncodeUtil.encodeBase64(encrypted);
    }

    public static byte[] encryptWithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        return !StringUtils.isBlank(data) && !StringUtils.isBlank(key) && padding != null ? XpAESUtil.doSymmetric(1, EncryptAlgorithmEnum.SYM_SM4, EncryptModeEnum.ECB, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), SecureUtil.decode(key), (byte[])null, padding) : null;
    }

    public static String decryptWithECB(final String data, final String key) {
        return decryptWithECB(data, key, StandardCharsets.UTF_8);
    }

    public static String decryptWithECB(final String data, final String key, final Charset charset) {
        SM4 algorithm = new SM4(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        return algorithm.decryptStr(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String decryptWithECB(final String data, final String key, final EncryptPaddingEnum padding, final Charset charset) {
        if (!StringUtils.isBlank(data) && !StringUtils.isBlank(key) && padding != null) {
            byte[] decrypted = XpAESUtil.doSymmetric(2, EncryptAlgorithmEnum.SYM_SM4, EncryptModeEnum.ECB, SecureUtil.decode(data), SecureUtil.decode(key), (byte[])null, padding);
            return new String(decrypted, charset == null ? StandardCharsets.UTF_8 : charset);
        } else {
            return null;
        }
    }

    public static String encryptHexWithCBC(final String data, final Charset charset, final String key, final String iv) {
        SM4 algorithm = new SM4(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return algorithm.encryptHex(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptHexWithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithCBC(data, charset, key, iv, padding);
        return XpEncodeUtil.encodeHex(encrypted);
    }

    public static String encryptBase64WithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithCBC(data, charset, key, iv, padding);
        return XpEncodeUtil.encodeBase64(encrypted);
    }

    public static byte[] encryptWithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        return !StringUtils.isBlank(data) && !StringUtils.isBlank(key) && !StringUtils.isBlank(iv) && padding != null ? XpAESUtil.doSymmetric(1, EncryptAlgorithmEnum.SYM_SM4, EncryptModeEnum.CBC, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), SecureUtil.decode(key), SecureUtil.decode(iv), padding) : null;
    }

    public static String decryptWithCBC(final String data, final String key, final String iv, final EncryptPaddingEnum padding, final Charset charset) {
        if (!StringUtils.isBlank(data) && !StringUtils.isBlank(key) && !StringUtils.isBlank(iv) && padding != null) {
            byte[] decrypted = XpAESUtil.doSymmetric(2, EncryptAlgorithmEnum.SYM_SM4, EncryptModeEnum.CBC, SecureUtil.decode(data), SecureUtil.decode(key), SecureUtil.decode(iv), padding);
            return new String(decrypted, charset == null ? StandardCharsets.UTF_8 : charset);
        } else {
            return null;
        }
    }
}
