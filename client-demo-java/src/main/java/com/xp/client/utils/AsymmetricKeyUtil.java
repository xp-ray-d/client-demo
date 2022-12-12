package com.xp.client.utils;


import com.xp.client.dto.XpKeystoreDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 非对称密钥工具类
 */
@Slf4j
public class AsymmetricKeyUtil {

    /**
     * 读取私钥
     *
     * @param keystore 密钥对象
     * @return 私钥
     */
    public static String getPrivateKeyStr(final XpKeystoreDto keystore) {
        if (null == keystore) {
            return null;
        }
        if (StringUtils.isNotBlank(keystore.getKeyValue())) {
            return keystore.getKeyValue();
        }

        // TODO 若从文件服务器读取文件则需要修改
        if (StringUtils.isNotBlank(keystore.getKeyUrl()) && StringUtils.isNotBlank(keystore.getKeyPsw())) {
            final RSAPrivateCrtKey rsaPrivateCrtKey = XpKeyUtil.getRsaPrivateKeyFromPfx(keystore.getKeyUrl(), keystore.getKeyPsw());
            return XpKeyUtil.getRsaPrivateKey(rsaPrivateCrtKey);
        }
        return null;
    }

    /**
     * 读取公钥
     *
     * @param keystore 密钥对象
     * @return 公钥
     */
    public static String getPublicKeyStr(final XpKeystoreDto keystore) {
        if (null == keystore) {
            return null;
        }
        if (StringUtils.isNotBlank(keystore.getKeyValue())) {
            return keystore.getKeyValue();
        }

        // TODO 若从文件服务器读取文件则需要修改
        if (StringUtils.isNotBlank(keystore.getKeyUrl())) {
            final RSAPublicKey rsaPublicKey = XpKeyUtil.getRsaPublicKeyFromCer(keystore.getKeyUrl());
            return XpKeyUtil.getRsaPublicKey(rsaPublicKey);
        }
        return null;
    }
}
