package com.xp.client.dto.v2;

import com.xp.client.dto.v2.body.ApiBodyNotifyRsp;
import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:47
 * @Description: API通知应答报文
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiNotifyRsp implements IApi {

    protected ApiBodyNotifyRsp body;

    public void check(final String origMsg) {
        if (this.getBody() == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "通知应答报文[body]为空：[" + origMsg + "]");
        }
        this.getBody().check(origMsg);
    }
}
