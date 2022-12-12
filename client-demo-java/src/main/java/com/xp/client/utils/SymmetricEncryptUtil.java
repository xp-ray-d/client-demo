package com.xp.client.utils;

import com.xp.client.enums.EncodeTypeEnum;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.EncryptModeEnum;
import com.xp.client.enums.EncryptPaddingEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import java.nio.charset.Charset;

public class SymmetricEncryptUtil {

    public SymmetricEncryptUtil() {
    }

    public static String encrypt(final String data, final Charset charset, final EncryptAlgorithmEnum encryptAlgorithm, final EncryptModeEnum encryptMode, final EncryptPaddingEnum encryptPadding, final String key, final String iv, final EncodeTypeEnum encodeType) {
        String params = String.format("[encryptAlgorithm: %s][encryptMode: %s][encryptPadding: %s][key: %s][iv: %s][encodeType: %s][charset: %s]", encryptAlgorithm, encryptMode, encryptPadding, encodeType, key, iv, charset == null ? "" : charset.name());
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(String.format("[%s]加密失败：明文为空", params));
        } else if (null == encryptAlgorithm) {
            throw new RuntimeException(String.format("[%s]加密失败：加密算法为空", params));
        } else if (null == encryptMode) {
            throw new RuntimeException(String.format("[%s]加密失败：加密模式[%s]不存在", params, encryptMode));
        } else if (null == encryptMode.getMode()) {
            throw new RuntimeException(String.format("[%s]加密失败：加密模式[%s]不支持", params, encryptMode));
        } else if (null == encryptPadding) {
            throw new RuntimeException(String.format("[%s]加密失败：填充模式[%s]不存在", params, encryptPadding));
        } else if (StringUtils.isBlank(key)) {
            throw new RuntimeException(String.format("[%s]加密失败：密钥为空", params));
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_AES.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return XpAESUtil.encryptHexWithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return XpAESUtil.encryptHexWithCBC(data, charset, key, iv, encryptPadding);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return XpAESUtil.encryptBase64WithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return XpAESUtil.encryptBase64WithCBC(data, charset, key, iv, encryptPadding);
                }
            } else {
                throw new RuntimeException(String.format("[%s]加密参数不正确", params));
            }
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_DES.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return XpDESUtil.encryptHexWithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return XpDESUtil.encryptHexWithCBC(data, charset, key, iv, encryptPadding);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return XpDESUtil.encryptBase64WithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return XpDESUtil.encryptBase64WithCBC(data, charset, key, iv, encryptPadding);
                }
            } else {
                throw new RuntimeException(String.format("[%s]加密参数不正确", params));
            }
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_3DES.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return Xp3DESUtil.encryptHexWithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return Xp3DESUtil.encryptHexWithCBC(data, charset, key, iv, encryptPadding);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return Xp3DESUtil.encryptBase64WithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return Xp3DESUtil.encryptBase64WithCBC(data, charset, key, iv, encryptPadding);
                }
            } else {
                throw new RuntimeException(String.format("[%s]加密参数不正确", params));
            }
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_SM4.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return XpSM4Util.encryptHexWithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return XpSM4Util.encryptHexWithCBC(data, charset, key, iv, encryptPadding);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return XpSM4Util.encryptBase64WithECB(data, charset, key, encryptPadding);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]加密失败：初始向量为空", params));
                } else {
                    return XpSM4Util.encryptBase64WithCBC(data, charset, key, iv, encryptPadding);
                }
            } else {
                throw new RuntimeException(String.format("[%s]加密参数不正确", params));
            }
        } else {
            throw new RuntimeException(String.format("[%s]加密失败：加密算法[%s]不支持", params, encryptAlgorithm));
        }
    }

    public static String decrypt(final String data, final EncodeTypeEnum encodeType, final EncryptAlgorithmEnum encryptAlgorithm, final EncryptModeEnum encryptMode, final EncryptPaddingEnum encryptPadding, final String key, final String iv, final Charset charset) {
        String params = String.format("[encryptAlgorithm: %s][encryptMode: %s][encryptPadding: %s][key: %s][iv: %s][encodeType: %s][charset: %s]", encryptAlgorithm, encryptMode, encryptPadding, encodeType, key, iv, charset == null ? "" : charset.name());
        if (StringUtils.isBlank(data)) {
            throw new RuntimeException(String.format("[%s]解密失败：密文为空", params));
        } else if (null == encryptAlgorithm) {
            throw new RuntimeException(String.format("[%s]解密失败：加密算法为空", params));
        } else if (null == encryptMode) {
            throw new RuntimeException(String.format("[%s]解密失败：加密模式[%s]不存在", params, encryptMode));
        } else if (null == encryptMode.getMode()) {
            throw new RuntimeException(String.format("[%s]解密失败：加密模式[%s]不支持", params, encryptMode));
        } else if (null == encryptPadding) {
            throw new RuntimeException(String.format("[%s]解密失败：填充模式[%s]不存在", params, encryptPadding));
        } else if (StringUtils.isBlank(key)) {
            throw new RuntimeException(String.format("[%s]解密失败：密钥为空", params));
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_AES.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return XpAESUtil.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return XpDESUtil.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return XpAESUtil.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return XpDESUtil.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else {
                throw new RuntimeException(String.format("[%s]解密参数不正确", params));
            }
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_DES.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return XpDESUtil.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return XpDESUtil.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return XpDESUtil.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return XpDESUtil.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else {
                throw new RuntimeException(String.format("[%s]解密参数不正确", params));
            }
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_3DES.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return Xp3DESUtil.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return Xp3DESUtil.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return Xp3DESUtil.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return Xp3DESUtil.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else {
                throw new RuntimeException(String.format("[%s]解密参数不正确", params));
            }
        } else if (StringUtils.equals(encryptAlgorithm.getValue(), EncryptAlgorithmEnum.SYM_SM4.getValue())) {
            if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                return XpSM4Util.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.HEX.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return XpSM4Util.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.ECB.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                return XpSM4Util.decryptWithECB(data, key, encryptPadding, charset);
            } else if (StringUtils.equals(encryptMode.getValue(), EncryptModeEnum.CBC.getValue()) && StringUtils.equals(encodeType.getValue(), EncodeTypeEnum.BASE64.getValue())) {
                if (StringUtils.isBlank(iv)) {
                    throw new RuntimeException(String.format("[%s]解密失败：初始向量为空", params));
                } else {
                    return XpSM4Util.decryptWithCBC(data, key, iv, encryptPadding, charset);
                }
            } else {
                throw new RuntimeException(String.format("[%s]解密参数不正确", params));
            }
        } else {
            throw new RuntimeException(String.format("[%s]解密失败：加密算法[%s]不支持", params, encryptAlgorithm));
        }
    }
}
