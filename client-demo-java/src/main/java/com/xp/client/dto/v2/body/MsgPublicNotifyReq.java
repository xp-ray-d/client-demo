package com.xp.client.dto.v2.body;

import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:43
 * @Description: 公共报文体（通知请求：平台 -> 客户）
 * @Version: 1.0
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgPublicNotifyReq {

    protected String txnDate;
    protected String cusCode;
    protected String cusTraceNo;
    protected String sysTraceNo;
    protected String sysReqTime;
    protected String rspCode;
    protected String rspMsg;

    public void check(final String origMsg) {
        if (StringUtils.isBlank(this.getTxnDate())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[txnDate]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getCusCode())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[cusCode]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getCusTraceNo())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[cusTraceNo]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getSysTraceNo())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[sysTraceNo]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getSysReqTime())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[sysReqTime]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getRspCode())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[rspCode]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getRspMsg())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[rspMsg]为空：[" + origMsg + "]");
        }
    }
}
