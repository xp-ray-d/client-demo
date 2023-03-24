package com.xp.client.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;

public class XpRSAUtil {

    public static String signBase64SHA256WithRSA(final String data, final Charset charset, final String privateKey) {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA, privateKey, (String)null);
        return Base64.encode(sign.sign(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset)));
    }

    public static String signBase64SHA512WithRSA(final String data, final Charset charset, final String privateKey) {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withRSA, privateKey, (String)null);
        return Base64.encode(sign.sign(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset)));
    }

    public static boolean verifyBase64SHA256WithRSA(final String data, final String publicKey, final String signVerify) {
        return verifyBase64SHA256WithRSA(data, StandardCharsets.UTF_8, publicKey, signVerify);
    }

    public static boolean verifyBase64SHA256WithRSA(final String data, final Charset charset, final String publicKey, final String signVerify) {
        byte[] signVerifyDecoded = Base64.decode(signVerify);
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA, (String)null, publicKey);
        return sign.verify(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), signVerifyDecoded);
    }

    public static boolean verifyBase64SHA512WithRSA(final String data, final Charset charset, final String publicKey, final String signVerify) {
        byte[] signVerifyDecoded = Base64.decode(signVerify);
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withRSA, (String)null, publicKey);
        return sign.verify(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), signVerifyDecoded);
    }

    public static String encryptBase64ByPublicKey(final String data, final Charset charset, final String publicKey) {
        byte[] encrypt = encryptByPublicKey(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), publicKey);
        return Base64.encode(encrypt);
    }

    public static String encryptBase64ByPublicKey(final String data, final Charset charset, final String publicKey, final String algorithm) {
        byte[] encrypt = encryptByPublicKey(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), publicKey, algorithm);
        return Base64.encode(encrypt);
    }

    public static byte[] encryptByPublicKey(final byte[] data, final String publicKey, final String algorithm) {
        String algo = StringUtils.isBlank(algorithm) ? AsymmetricAlgorithm.RSA.getValue() : algorithm;
        RSA rsa = new RSA(algo, (String)null, publicKey);
        return rsa.encrypt(data, KeyType.PublicKey);
    }

    public static byte[] encryptByPublicKey(final byte[] data, final String publicKey) {
        RSA algorithm = new RSA(AsymmetricAlgorithm.RSA.getValue(), (String)null, publicKey);
        return algorithm.encrypt(data, KeyType.PublicKey);
    }

    public static String decryptBase64ByPrivateKey(final String data, final Charset charset, final String privateKey) {
        byte[] dataDecoded = Base64.decode(data);
        byte[] decrypt = decryptByPrivateKey(dataDecoded, privateKey);
        return new String(decrypt, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static String decryptBase64ByPrivateKey(final String data, final Charset charset, final String privateKey, final String algorithm) {
        byte[] dataDecoded = Base64.decode(data);
        byte[] decrypt = decryptByPrivateKey(dataDecoded, privateKey, algorithm);
        return new String(decrypt, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static byte[] decryptByPrivateKey(final byte[] data, final String privateKey, final String algorithm) {
        String algo = StringUtils.isBlank(algorithm) ? AsymmetricAlgorithm.RSA.getValue() : algorithm;
        RSA rsa = new RSA(algo, privateKey, (String)null);
        return rsa.decrypt(data, KeyType.PrivateKey);
    }

    public static byte[] decryptByPrivateKey(final byte[] data, final String privateKey) {
        RSA algorithm = new RSA(AsymmetricAlgorithm.RSA.getValue(), privateKey, (String)null);
        return algorithm.decrypt(data, KeyType.PrivateKey);
    }
}
