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
 * @Description: 公共报文体（通知应答：客户 -> 平台）
 * @Version: 1.0
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgPublicNotifyRsp {

    protected String rspCode;

    public void check(final String origMsg) {
        if (StringUtils.isBlank(this.getRspCode())) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "应答报文[rspCode]为空：[" + origMsg + "]");
        }
    }
}
