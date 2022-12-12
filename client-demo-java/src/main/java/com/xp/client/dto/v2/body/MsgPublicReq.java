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
 * @Description: 公共报文体（请求：客户 -> 平台）
 * @Version: 1.0
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgPublicReq {

    protected String version;
    protected String cusReqTime;
    protected String cusTraceNo;
    protected String agentCode;
    protected String cusCode;

    public void check(final String origMsg) {
        if (StringUtils.isBlank(this.getVersion())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[version]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getCusReqTime())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[cusReqTime]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getCusTraceNo())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[cusTraceNo]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getCusCode())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[cusCode]为空：[" + origMsg + "]");
        }
    }
}
