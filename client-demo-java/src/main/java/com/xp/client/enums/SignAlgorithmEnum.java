package com.xp.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 签名算法=摘要+非对称加密 cn.hutool.crypto.asymmetric.SignAlgorithm
 */
@Getter
@AllArgsConstructor
public enum SignAlgorithmEnum {

    SHA256withRSA("SHA256withRSA"),
    SHA512withRSA("SHA512withRSA"),
    SM2("SM2");

    private String value;

    public static SignAlgorithmEnum findEnum(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final SignAlgorithmEnum algorithm : values()) {
            if (algorithm.getValue().equals(value)) {
                return algorithm;
            }
        }
        return null;
    }
}
