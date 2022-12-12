package com.xp.client.enums;

import java.text.MessageFormat;

public enum RspCodeStdEnum implements IRspCode {

    SUCCESS_SYNC("0000", "交易成功", ""),
    SUCCESS_SUBCODE_CHARGE("0998", "交易成功{0}", "[{0}-{1}]"),
    SUCCESS_SUBCODE_FREE("0999", "交易成功{0}", "[{0}-{1}]"),
    UNKNOWN_PENDING("2001", "交易待处理", ""),
    UNKNOWN_HANDLING("2002", "交易处理中", ""),
    ERROR_SYS("1000", "系统错误"),
    ERROR_REQ_TYPE("1001", "请求方式不正确"),
    ERROR_NO_URI("1002", "请求地址不存在"),
    ERROR_NO_AUTH("1003", "无此交易权限"),
    ERROR_SIGNATURE("1010", "签名错误"),
    ERROR_DECRYPT("1011", "解密失败"),
    ERROR_MSG_FORMAT("1012", "报文格式错误"),
    ERROR_PARAM_MISSING("1013", "报文参数缺失"),
    ERROR_SYS_CFG("1014", "系统配置错误"),
    ERROR_SYS_CLOSED("1015", "系统未开放或不在受理时间"),
    ERROR_ILLEGAL("1020", "非法请求"),
    ERROR_SYS_BUSY("1021", "系统繁忙"),
    ERROR_TOO_MANY_ATTEMPTS("1022", "尝试次数过多"),
    ERROR_TOO_FREQUENT("1023", "请求过于频繁"),
    ERROR_RISK("1024", "风险受限"),
    ERROR_MSG_PARAM("1040", "报文参数错误"),
    ERROR_MSG_PARAM_TOO_LONG("1041", "报文参数过长"),
    ERROR_TXN_TIME("1042", "交易时间错误"),
    ERROR_ORDER_NO_DUPLICATE("1050", "订单号重复"),
    ERROR_ORDER_NOT_EXIST("1051", "订单不存在"),
    ERROR_ORDER_STATE("1052", "订单状态异常"),
    ERROR_ORDER_UNSUPPORT("1053", "订单不支持该操作"),
    ERROR_ORDER_NO_QUERY("1054", "该订单不支持查询"),
    ERROR_ORDER_NO_REFUND("1055", "该订单不支持退款"),
    ERROR_CUS_NOT_EXIST("1061", "客户不存在"),
    ERROR_CUS_STATE("1062", "客户状态不正确"),
    ERROR_PARTNER_NOT_EXIST("1063", "合作方不存在"),
    ERROR_PARTNER_STATE("1064", "合作方状态不正确"),
    ERROR_PARTNER_XREF("1065", "合作方关联不匹配"),
    ERROR_PRODUCT_NOT_EXIST("1071", "产品不存在"),
    ERROR_PRODUCT_STATE("1072", "产品未启用"),
    ERROR_CUS_PRODUCT_XREF("1073", "未开通该产品"),
    ERROR_ACCT_NOT_EXIST("1081", "账号不存在"),
    ERROR_ACCT_STATE("1082", "账号未启用"),
    ERROR_ACCT_INFO("1083", "账户信息不正确或已过期"),
    ERROR_ACCT_BALANCE("1013", "账户余额不足"),
    ERROR_LIMIT_TOTAL("1013", "额度不足"),
    ERROR_LIMIT_SINGLE("1013", "订单金额受限"),
    ERROR_AUDIT("1090", "审核不通过"),
    ERROR_OTHER("1099", "其他错误"),
    ERROR_NETWORK("1200", "网络异常"),
    ERROR_CHNL_NO_REPLY("1201", "通道无应答"),
    ERROR_CHNL_SYS("1202", "通道系统异常"),
    ERROR_CHNL_CLOSED("1220", "通道未开放或不在受理时间"),
    ERROR_CHNL_NOT_SUPPORT("1221", "通道不支持该交易"),
    ERROR_CHNL_TXN_REFUSE("1222", "通道交易受限"),
    ERROR_CHNL_RISK("1223", "通道风险受限"),
    ERROR_CHNL_SYS_BUSY("1224", "通道系统繁忙，请稍后重试"),
    ERROR_CHNL_OTHER("1299", "通道其他错误{0}", "[{0}-{1}]");

    private final String code;
    private final String msg;
    private final String remark;

    private RspCodeStdEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.remark = "";
    }

    private RspCodeStdEnum(String code, String msg, String remark) {
        this.code = code;
        this.msg = msg;
        this.remark = remark;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getMsg(final String subCode, final String subMsg) {
        return MessageFormat.format(this.msg, this.getRemark(subCode, subMsg));
    }

    public String getRemark(final String subCode, final String subMsg) {
        return MessageFormat.format(this.remark, subCode, subMsg);
    }

    public String toString() {
        return "[code]" + this.getCode() + "; [msg]" + this.getMsg();
    }
}
