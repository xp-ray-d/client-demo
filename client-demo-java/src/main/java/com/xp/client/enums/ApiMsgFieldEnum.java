package com.xp.client.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ray
 * @CreateTime: 20221017
 * @Description: API报文字段
 * @Version: 1.0
 */
@Getter
public enum ApiMsgFieldEnum {

    AUTH_CODE("authCode", "支付授权码"),
    TXN_AMT("txnAmt", "交易金额"),
    TXN_REMARK("txnRemark", "订单附言"),
    MEMO("memo", "附加数据"),

    BSN_INFO("bsnInfo", "订单信息 BsnInfo.java"),

    SETT("sett", "结算标识"),
    SPLIT("split", "分账标识"),
    RECEIVER("receiver", "分账方 SplitReceiver.java"),
    UNFREEZE("unfreeze", "解冻标识"),

    PAY_URL("payUrl", "支付链接"),
    NOTIFY_URL("notifyUrl", "后端异步通知地址"),
    COMPLETE_URL("completeUrl", "支付取消后前端跳转地址"),
    CANCEL_URL("cancelUrl", "支付取消后前端跳转地址"),

    ORIG_TXN_DATE("origTxnDate", "原交易日期"),
    ORIG_CUS_TRACE_NO("origCusTraceNo", "原交易客户订单号"),
    ORIG_SYS_TRACE_NO("origSysTraceNo", "原交易系统跟踪号"),

    SCENE("scene", "业务场景 Scene.java"),
    PAYER("payer", "付款方信息 Payer.java"),
    ;

    ApiMsgFieldEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public static ApiMsgFieldEnum findEnumByCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (final ApiMsgFieldEnum value : ApiMsgFieldEnum.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }
}
