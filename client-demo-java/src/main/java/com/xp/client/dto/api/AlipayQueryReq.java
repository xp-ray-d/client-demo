package com.xp.client.dto.api;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

;

@Data
public class AlipayQueryReq implements Serializable {

    private static final long serialVersionUID = -5512746239657103037L;
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

        private String origTxnDate;
        private String origCusTraceNo;
        private String origSysTraceNo;
    }

}
