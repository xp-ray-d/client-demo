package com.xp.client.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.crypto.engines.SM2Engine;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XpSM2Util {

    public static String signBase64WithSM2(final String data, final Charset charset, final String privateKey) {
        SM2 algorithm = SmUtil.sm2(privateKey, (String)null);
        algorithm.setMode(SM2Engine.Mode.C1C3C2);
        return Base64.encode(algorithm.sign(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset)));
    }

    public static boolean verifyBase64WithSM2(final String data, final Charset charset, final String publicKey, final String signVerify) {
        SM2 algorithm = SmUtil.sm2((String)null, publicKey);
        algorithm.setMode(SM2Engine.Mode.C1C3C2);
        byte[] signVerifyDecoded = Base64.decode(signVerify);
        return algorithm.verify(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), signVerifyDecoded);
    }

    public static String encryptBase64ByPublicKey(final String data, final Charset charset, final String publicKey) {
        byte[] encrypt = encryptByPublicKey(data.getBytes(charset == null ? StandardCharsets.UTF_8 : charset), publicKey);
        return Base64.encode(encrypt);
    }

    public static byte[] encryptByPublicKey(final byte[] data, final String publicKey) {
        SM2 algorithm = SmUtil.sm2((String)null, publicKey);
        algorithm.setMode(SM2Engine.Mode.C1C3C2);
        return algorithm.encrypt(data, KeyType.PublicKey);
    }

    public static String decryptBase64ByPrivateKey(final String data, final Charset charset, final String privateKey) {
        byte[] dataDecoded = Base64.decode(data);
        byte[] decrypt = decryptByPrivateKey(dataDecoded, privateKey);
        return new String(decrypt, charset == null ? StandardCharsets.UTF_8 : charset);
    }

    public static byte[] decryptByPrivateKey(final byte[] data, final String privateKey) {
        SM2 algorithm = SmUtil.sm2(privateKey, (String)null);
        algorithm.setMode(SM2Engine.Mode.C1C3C2);
        return algorithm.decrypt(data, KeyType.PrivateKey);
    }
}
