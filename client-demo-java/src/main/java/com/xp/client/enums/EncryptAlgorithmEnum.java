package com.xp.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 加密算法
 */
@Getter
@AllArgsConstructor
public enum EncryptAlgorithmEnum {

    // 非对称加密算法
    ASYM_RSA("RSA"),
    //ASYM_ECC("DSA"),
    //ASYM_ECC("ECC"),
    ASYM_SM2("SM2"),

    // 对称加密算法
    SYM_DES("DES"),
    SYM_3DES("DESede"),
    SYM_AES("AES"),
    SYM_SM4("SM4");

    private String value;

    public static EncryptAlgorithmEnum findEnum(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final EncryptAlgorithmEnum obj : values()) {
            if (obj.getValue().equals(value)) {
                return obj;
            }
        }
        return null;
    }
}
