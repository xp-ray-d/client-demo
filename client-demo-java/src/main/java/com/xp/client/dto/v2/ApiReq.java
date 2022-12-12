package com.xp.client.dto.v2;

import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:47
 * @Description: API请求报文
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiReq implements IApi {

    protected String body;
    protected String sign;

    public void check(final String origMsg) {
        if (StringUtils.isBlank(this.getBody())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[body]为空：[" + origMsg + "]");
        } else if (StringUtils.isBlank(this.getSign())) {
            throw new BaseException(RspCodeStdEnum.ERROR_MSG_FORMAT, "请求报文[sign]为空：[" + origMsg + "]");
        }
    }
}
