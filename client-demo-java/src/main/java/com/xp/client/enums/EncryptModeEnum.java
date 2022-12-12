package com.xp.client.enums;

import cn.hutool.crypto.Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 对称加密算法.加密模式
 */
@Getter
@AllArgsConstructor
public enum EncryptModeEnum {

    CBC("CBC", Mode.CBC),
    ECB("ECB", Mode.ECB);

    private String value;
    private Mode mode;

    public static EncryptModeEnum findEnum(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final EncryptModeEnum obj : values()) {
            if (obj.getValue().equals(value)) {
                return obj;
            }
        }
        return null;
    }
}
