package com.xp.client.enums;

import cn.hutool.crypto.Padding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 对称加密算法.填充模式
 */
@Getter
@AllArgsConstructor
public enum EncryptPaddingEnum {

    ZeroPadding("ZeroBytePadding", Padding.ZeroPadding),
    PKCS1Padding("PKCS1Padding", Padding.PKCS1Padding),
    PKCS5Padding("PKCS5Padding", Padding.PKCS5Padding),
    PKCS6Padding("PKCS6Padding", null),
    PKCS7Padding("PKCS7Padding", null),
    PKCS8Padding("PKCS8Padding", null);

    private String value;
    private Padding padding;

    public static EncryptPaddingEnum findEnum(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final EncryptPaddingEnum obj : values()) {
            if (obj.getValue().equals(value)) {
                return obj;
            }
        }
        return null;
    }
}
