package com.xp.client.dto.v2;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:47
 * @Description: API应答报文
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiRsp implements IApi {

    protected String body;
    protected String sign;

    public void check(final String origMsg) {
    }
}
