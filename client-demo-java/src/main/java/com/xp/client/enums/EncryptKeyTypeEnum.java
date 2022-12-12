package com.xp.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 非对称加密算法.密钥类型
 */
@Getter
@AllArgsConstructor
public enum EncryptKeyTypeEnum {

    SYM("sym", "对称密钥"),
    ASYM_PRIVATE("asym_private", "非对称私钥"),
    ASYM_PUBLIC("asym_public", "非对称公钥"),
    ASYM_PRIVATE_PLAT("asym_private_plat", "平台非对称私钥"),
    ASYM_PUBLIC_PLAT("asym_public_plat", "平台非对称公钥");

    private String value;
    private String desc;

    public static EncryptKeyTypeEnum findEnum(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final EncryptKeyTypeEnum obj : values()) {
            if (obj.getValue().equals(value)) {
                return obj;
            }
        }
        return null;
    }
}
