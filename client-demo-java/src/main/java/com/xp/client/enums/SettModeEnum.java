package com.xp.client.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: ray
 * @date: 20221007
 * @desc: 结算模式
 * @version: 1.0
 */
@Getter
public enum SettModeEnum {

    SETT_AUTO("auto", "0", "自动结算/通道自动结算"),
    SETT_MANUAL("manual", "1", "手工结算/客户自主结算"),
    ;

    private String code;
    private String value;
    private String desc;

    SettModeEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public static SettModeEnum findEnumByValue(final String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (final SettModeEnum obj : SettModeEnum.values()) {
            if (StringUtils.equals(obj.getValue(), value)) {
                return obj;
            }
        }
        return null;
    }
}
