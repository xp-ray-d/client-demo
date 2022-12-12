package com.xp.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum SplitReceiverEnum {

    ALIPAY_USER_ID("alipayUserId",  "支付宝用户id(2088开头的16位数字)"),
    ALIPAY_LOGIN_NAME("alipayLoginName",  "支付宝用户登录名(手机号/邮箱等)"),
    WXPAY_MER("wxpayMer",  "微信商户号(mch_id或者sub_mch_id)"),
    WXPAY_MER_USER("wxpayMerUser",  "微信个人openid(由父商户APPID转换得到)"),
    WXPAY_SUB_MER_USER("wxpaySubMerUser",  "微信个人sub_openid (由子商户APPID转换得到)"),
    ;

    private String code;
    private String desc;

    public static SplitReceiverEnum findEnumByCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (final SplitReceiverEnum value : SplitReceiverEnum.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }
}
