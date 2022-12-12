package com.xp.client.dto.v2;

import com.xp.client.dto.v2.body.ApiBodyRspExt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:47
 * @Description: API应答报文（扩展，解析为对象）
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiRspExt extends ApiRsp {

    protected ApiBodyRspExt objBody;

    public void check(final String origMsg) {
        super.check(origMsg);

        this.getObjBody().check(origMsg);
        this.getObjBody().getMsgPublic().check(origMsg);
    }
}
