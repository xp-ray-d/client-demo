package com.xp.client.dto.v2.body;

import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:44
 * @Description: API通知应答报文体
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiBodyNotifyRsp {

    protected MsgPublicNotifyRsp msgPublic;

    public void check(final String origMsg) {
        if (this.getMsgPublic() == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "应答报文[msgPublic]为空：[" + origMsg + "]");
        }
    }
}
