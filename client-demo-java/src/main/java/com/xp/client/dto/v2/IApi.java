package com.xp.client.dto.v2;

import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.enums.RspCodeStdEnum;
import com.xp.client.utils.BaseException;
import com.xp.client.utils.SecurityUtil;

/**
 * @Author: ray
 * @CreateTime: 2022-08-16 14:46
 * @Description: API接口
 * @Version: 1.0
 */
public interface IApi {

    default String sign(final XpKeystoreDto keystore, final String bodyJson) {
        if (keystore == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "签名私钥对象为空");
        }
        final String sign = SecurityUtil.sign(bodyJson, keystore);
        return sign;
    }

    default boolean verifySign(final XpKeystoreDto keystore, final String bodyJson, final String sign) {
        if (keystore == null) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, "验签公钥对象为空");
        }
        final boolean verify = SecurityUtil.verifySign(bodyJson, sign, keystore);
        return verify;
    }
}
