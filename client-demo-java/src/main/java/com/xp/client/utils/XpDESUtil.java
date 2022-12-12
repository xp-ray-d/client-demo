package com.xp.client.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.EncryptModeEnum;
import com.xp.client.enums.EncryptPaddingEnum;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XpDESUtil {

    public XpDESUtil() {
    }

    public static String encryptHexWithECB(final String data, final String key) {
        return encryptHexWithECB(data, StandardCharsets.UTF_8, key);
    }

    public static String encryptHexWithECB(final String data, final Charset charset, final String key) {
        DES algorithm = new DES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        return algorithm.encryptHex(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptHexWithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithECB(data, charset, key, padding);
        return XpEncodeUtil.encodeHex(encrypted);
    }

    public static String encryptBase64WithECB(final String data, final String key) {
        return encryptBase64WithECB(data, StandardCharsets.UTF_8, key);
    }

    public static String encryptBase64WithECB(final String data, final Charset charset, final String key) {
        DES algorithm = new DES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        return algorithm.encryptBase64(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptBase64WithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithECB(data, charset, key, padding);
        return XpEncodeUtil.encodeBase64(encrypted);
    }

    public static byte[] encryptWithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        return !StringUtils.isBlank(data) && !StringUtils.isBlank(key) && padding != null ? XpAESUtil.doSymmetric(1, EncryptAlgorithmEnum.SYM_DES, EncryptModeEnum.ECB, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), SecureUtil.decode(key), (byte[])null, padding) : null;
    }

    public static String decryptWithECB(final String data, final String key) {
        return decryptWithECB(data, key, StandardCharsets.UTF_8);
    }

    public static String decryptWithECB(final String data, final String key, final Charset charset) {
        DES algorithm = new DES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        return algorithm.decryptStr(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String decryptWithECB(final String data, final String key, final EncryptPaddingEnum padding, final Charset charset) {
        if (!StringUtils.isBlank(data) && !StringUtils.isBlank(key) && padding != null) {
            byte[] decrypted = XpAESUtil.doSymmetric(2, EncryptAlgorithmEnum.SYM_DES, EncryptModeEnum.ECB, SecureUtil.decode(data), SecureUtil.decode(key), (byte[])null, padding);
            return new String(decrypted, charset == null ? StandardCharsets.UTF_8 : charset);
        } else {
            return null;
        }
    }

    public static String encryptHexWithCBC(final String data, final String key, final String iv) {
        return encryptHexWithCBC(data, StandardCharsets.UTF_8, key, iv);
    }

    public static String encryptHexWithCBC(final String data, final Charset charset, final String key, final String iv) {
        DES algorithm = new DES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return algorithm.encryptHex(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptHexWithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithCBC(data, charset, key, iv, padding);
        return XpEncodeUtil.encodeHex(encrypted);
    }

    public static String encryptBase64WithCBC(final String data, final String key, final String iv) {
        return encryptBase64WithCBC(data, StandardCharsets.UTF_8, key, iv);
    }

    public static String encryptBase64WithCBC(final String data, final Charset charset, final String key, final String iv) {
        DES algorithm = new DES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return algorithm.encryptBase64(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String encryptBase64WithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithCBC(data, charset, key, iv, padding);
        return XpEncodeUtil.encodeBase64(encrypted);
    }

    public static byte[] encryptWithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        return !StringUtils.isBlank(data) && !StringUtils.isBlank(key) && !StringUtils.isBlank(iv) && padding != null ? XpAESUtil.doSymmetric(1, EncryptAlgorithmEnum.SYM_DES, EncryptModeEnum.CBC, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), SecureUtil.decode(key), SecureUtil.decode(iv), padding) : null;
    }

    public static String decryptWithCBC(final String data, final String key, final String iv) {
        return decryptWithCBC(data, key, iv, StandardCharsets.UTF_8);
    }

    public static String decryptWithCBC(final String data, final String key, final String iv, final Charset charset) {
        DES algorithm = new DES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return algorithm.decryptStr(data, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String decryptWithCBC(final String data, final String key, final String iv, final EncryptPaddingEnum padding, final Charset charset) {
        if (!StringUtils.isBlank(data) && !StringUtils.isBlank(key) && !StringUtils.isBlank(iv) && padding != null) {
            byte[] decrypted = XpAESUtil.doSymmetric(2, EncryptAlgorithmEnum.SYM_DES, EncryptModeEnum.CBC, SecureUtil.decode(data), SecureUtil.decode(key), SecureUtil.decode(iv), padding);
            return new String(decrypted, charset == null ? StandardCharsets.UTF_8 : charset);
        } else {
            return null;
        }
    }
}
