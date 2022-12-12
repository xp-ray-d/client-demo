package com.xp.client.utils;

import org.apache.commons.lang3.StringUtils;
import java.nio.charset.Charset;

public class XpSignUtil {

    public XpSignUtil() {
    }

    public static String sign(final String data, final Charset charset, final String signAlgorithm, final String privateKey) {
        String params = String.format("[signAlgorithm: %s][charset: %s]", signAlgorithm, charset.name());
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(String.format("[%s]签名失败：待签名数据为空", params));
        } else if (StringUtils.isBlank(privateKey)) {
            throw new RuntimeException(String.format("[%s]签名失败：密钥为空", params));
        } else if (StringUtils.isBlank(signAlgorithm)) {
            throw new RuntimeException(String.format("[%s]签名失败：签名算法为空", params));
        } else if (StringUtils.equals(signAlgorithm, "SHA256withRSA")) {
            return XpRSAUtil.signBase64SHA256WithRSA(data, charset, privateKey);
        } else if (StringUtils.equals(signAlgorithm, "SHA512withRSA")) {
            return XpRSAUtil.signBase64SHA512WithRSA(data, charset, privateKey);
        } else if (StringUtils.equals(signAlgorithm, "SM2")) {
            return XpSM2Util.signBase64WithSM2(data, charset, privateKey);
        } else {
            throw new RuntimeException(String.format("[%s]签名失败：签名算法[%s]不支持", params, signAlgorithm));
        }
    }

    public static boolean verify(final String data, final Charset charset, final String signAlgorithm, final String publicKey, final String signVerify) {
        String params = String.format("[signAlgorithm: %s][charset: %s][signVerify: %s]", signAlgorithm, charset.name(), signVerify);
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(String.format("[%s]签名失败：待签名数据为空", params));
        } else if (StringUtils.isBlank(publicKey)) {
            throw new RuntimeException(String.format("[%s]签名失败：密钥为空", params));
        } else if (StringUtils.isBlank(signAlgorithm)) {
            throw new RuntimeException(String.format("[%s]签名失败：签名算法为空", params));
        } else if (StringUtils.equals(signAlgorithm, "SHA256withRSA")) {
            return XpRSAUtil.verifyBase64SHA256WithRSA(data, charset, publicKey, signVerify);
        } else if (StringUtils.equals(signAlgorithm, "SHA512withRSA")) {
            return XpRSAUtil.verifyBase64SHA512WithRSA(data, charset, publicKey, signVerify);
        } else if (StringUtils.equals(signAlgorithm, "SM2")) {
            return XpSM2Util.verifyBase64WithSM2(data, charset, publicKey, signVerify);
        } else {
            throw new RuntimeException(String.format("[%s]签名失败：签名算法[%s]不支持", params, signAlgorithm));
        }
    }




}
