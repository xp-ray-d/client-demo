package com.xp.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 可见字符串编码类型
 */
@Getter
@AllArgsConstructor
public enum EncodeTypeEnum {

    HEX("HEX"),
    BASE64("BASE64");

    private String value;

    public static EncodeTypeEnum findEnum(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final EncodeTypeEnum obj : values()) {
            if (obj.getValue().equals(value)) {
                return obj;
            }
        }
        return null;
    }
}
