package com.xp.client.dto.v2;

import cn.hutool.json.JSONUtil;
import com.xp.client.dto.v2.body.ApiBodyReqExt;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:47
 * @Description: API请求报文（扩展，解析为对象）
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class ApiReqExt extends ApiReq {

    protected ApiBodyReqExt objBody;

    public ApiReqExt(final ApiReq apiReq) {
        this.setBody(apiReq.getBody());
        this.setSign(apiReq.getSign());
        this.setObjBody(JSONUtil.toBean(this.getBody(), ApiBodyReqExt.class));
    }

    public ApiReq getApiReq() {
        final ApiReq apiReq = new ApiReq();
        apiReq.setBody(this.getBody());
        apiReq.setSign(this.getSign());
        return apiReq;
    }

    public void check(final String origMsg) {
        super.check(body);

        this.getObjBody().check(origMsg);
        this.getObjBody().getMsgPublic().check(origMsg);
    }
}
