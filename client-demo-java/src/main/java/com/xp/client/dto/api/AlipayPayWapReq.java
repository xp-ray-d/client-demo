package com.xp.client.dto.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlipayPayWapReq implements Serializable {

    private static final long serialVersionUID = -2902859397615342030L;
    private Body body;
    private String sign;

    @Data
    public class Body {

        private MsgPublic msgPublic;
        private MsgPrivate msgPrivate;
    }

    @Data
    public static class MsgPublic {

        @NotNull(message = "版本号不能为空")
        private String version; //版本号
        @NotNull(message = "请求报文发送时间不能为空")
        private Date cusReqTime; //请求报文发送时间
        @NotNull(message = "客户支付订单号不能为空")
        private String cusTraceNo; //客户支付订单号
        @NotNull(message = "客户代码不能为空")
        private String cusCode; //客户代码
    }

    @Data
    public static class MsgPrivate {

        @NotNull(message = "支付金额不能为空")
        @Min(value = 1, message = "支付金额必须大于0")
        private Integer txnAmt; //支付金额
        private String currency; //币种
        @NotNull(message = "订单信息不能为空")
        private BsnInfo bsnInfo; //订单信息
        private String txnRemark; //交易附言
        private String memo; //附加数据
        private String notifyUrl; //后台通知地址
        private String cancelUrl; //支付取消后时的前端跳转地址
        private String completeUrl; //支付完成后的前台跳转地址
        private Integer sett; //结算标识 默认0，0：自动确认结算，1：手工确认结算
        private Integer split; //分账标识
        private List<Receiver> receiver; //分账方
    }

    @Data
    public static class BsnInfo {

        @NotNull(message = "订单标题不能为空")
        private String subject; //订单标题
        private String sn; //订单编号
        private Integer amt; //订单金额
        private Date expire; //订单付款超时时间
    }

    @Data
    public static class Receiver {

        private String type; //分账方类型
        private String acct; //分账方账号
        private String name; //分账方名称
        private String amt; //分账金额
        private String memo; //分账备注
    }


}
