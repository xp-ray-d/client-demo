package com.xp.client.utils;

import cn.hutool.crypto.SecureUtil;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.EncryptModeEnum;
import com.xp.client.enums.EncryptPaddingEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.text.MessageFormat;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class XpAESUtil {

    public static String decryptWithECB(final String data, final String key, final EncryptPaddingEnum padding, final Charset charset) {
        if (!StringUtils.isBlank(data) && !StringUtils.isBlank(key) && padding != null) {
            byte[] decrypted = doSymmetric(2, EncryptAlgorithmEnum.SYM_AES, EncryptModeEnum.ECB, SecureUtil.decode(data), SecureUtil.decode(key), (byte[])null, padding);
            return new String(decrypted, charset == null ? StandardCharsets.UTF_8 : charset);
        } else {
            return null;
        }
    }

    public static byte[] doSymmetric(final int mode, final EncryptAlgorithmEnum algorithmEnum, final EncryptModeEnum algorithmMode, final byte[] data, final byte[] key, final byte[] iv, final EncryptPaddingEnum padding) {
        if (algorithmEnum != null && algorithmMode != null && !ArrayUtils.isEmpty(data) && !ArrayUtils.isEmpty(key) && padding != null) {
            try {
                if (Security.getProvider("BC") == null) {
                    Security.addProvider(new BouncyCastleProvider());
                }

                Security.setProperty("crypto.policy", "unlimited");
                String algorithm = MessageFormat.format("{0}/{1}/{2}", algorithmEnum.getValue(), algorithmMode.getValue(), padding.getValue());
                Cipher cipher = Cipher.getInstance(algorithm);
                SecretKeySpec secretKey = new SecretKeySpec(key, algorithmEnum.getValue());
                if (ArrayUtils.isEmpty(iv)) {
                    cipher.init(mode, secretKey);
                } else {
                    cipher.init(mode, secretKey, new IvParameterSpec(iv));
                }

                return cipher.doFinal(data);
            } catch (Exception var10) {
                throw new RuntimeException(var10);
            }
        } else {
            return null;
        }
    }

    public static String encryptHexWithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithECB(data, charset, key, padding);
        return XpEncodeUtil.encodeHex(encrypted);
    }

    public static byte[] encryptWithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        return !StringUtils.isBlank(data) && !StringUtils.isBlank(key) && padding != null ? doSymmetric(1, EncryptAlgorithmEnum.SYM_AES, EncryptModeEnum.ECB, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), SecureUtil.decode(key), (byte[])null, padding) : null;
    }

    public static String encryptHexWithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithCBC(data, charset, key, iv, padding);
        return XpEncodeUtil.encodeHex(encrypted);
    }

    public static byte[] encryptWithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        return !StringUtils.isBlank(data) && !StringUtils.isBlank(key) && !StringUtils.isBlank(iv) && padding != null ? XpAESUtil.doSymmetric(1, EncryptAlgorithmEnum.SYM_AES, EncryptModeEnum.CBC, data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), SecureUtil.decode(key), SecureUtil.decode(iv), padding) : null;
    }

    public static String encryptBase64WithECB(final String data, final Charset charset, final String key, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithECB(data, charset, key, padding);
        return XpEncodeUtil.encodeBase64(encrypted);
    }

    public static String encryptBase64WithCBC(final String data, final Charset charset, final String key, final String iv, final EncryptPaddingEnum padding) {
        byte[] encrypted = encryptWithCBC(data, charset, key, iv, padding);
        return XpEncodeUtil.encodeBase64(encrypted);
    }
}
