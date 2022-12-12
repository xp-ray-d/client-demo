package com.xp.client.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: ray
 * @date: 20220908
 * @desc: 分账模式
 * @version: 1.0
 */
@Getter
public enum SplitModeEnum {

    SPLIT_NONE("none", "0", "不分账"),
    SPLIT_ONCE("once", "1", "一次分账"),
    SPLIT_MANY("many", "2", "多次分账"),
    ;

    private String code;
    private String value;
    private String desc;

    SplitModeEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public static SplitModeEnum findEnumByValue(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final SplitModeEnum obj : SplitModeEnum.values()) {
            if (StringUtils.equals(obj.getValue(), value)) {
                return obj;
            }
        }
        return null;
    }
}
