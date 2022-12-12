package com.xp.client.dto.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlipayRefundReq implements Serializable {

    private static final long serialVersionUID = -2160110220269987484L;
    private Body body;

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

        private String origTxnDate; //原订单日期
        private String origSysTraceNo; //原支付订单的系统跟踪号
        private Integer txnAmt; //退款金额
        private Integer split; //退分账标识
        private List<Receiver> receiver; //退款分账方
        private String txnRemark; //退款附言
    }

    @Data
    public static class Receiver {

        private String type; //分账方类型
        private String acct; //分账方账号
        private String amt; //退分账金额
        private String memo; //退分账备注
    }
}
