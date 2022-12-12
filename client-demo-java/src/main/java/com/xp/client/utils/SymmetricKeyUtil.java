package com.xp.client.utils;

import cn.hutool.core.codec.Base64;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.RspCodeStdEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;

/**
 * 密钥工具类
 */
@Slf4j
public class SymmetricKeyUtil {

    /**
     * 随机生成密钥
     *
     * @param symmetricAlgorithm 对称加密算法
     * @return 密钥
     */
    public static String getSymmetricKey(final String symmetricAlgorithm) {
        try {
            if (EncryptAlgorithmEnum.SYM_AES.getValue().equals(symmetricAlgorithm)
                || EncryptAlgorithmEnum.SYM_DES.getValue().equals(symmetricAlgorithm)
                || EncryptAlgorithmEnum.SYM_3DES.getValue().equals(symmetricAlgorithm)) {
                return genSymmetricKeyBase64(EncryptAlgorithmEnum.SYM_AES.getValue(), 128, null);
            } else if (EncryptAlgorithmEnum.SYM_SM4.getValue().equals(symmetricAlgorithm)) {
                return genSymmetricKeyBase64(EncryptAlgorithmEnum.SYM_AES.getValue(), 128, null);
            } else {
                throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, MessageFormat.format("随机生成密钥失败：对称加密算法[{0}]暂不支持", symmetricAlgorithm));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new BaseException(RspCodeStdEnum.ERROR_PARAM_MISSING, MessageFormat.format("随机生成密钥失败：{0}", e.getMessage()));
        }
    }

    /**
     * 随机生成初始向量
     *
     * @param symmetricAlgorithm 对称加密算法
     * @return 初始向量
     */
    public static String getSymmetricIv(final String symmetricAlgorithm) {
        try {
            if (EncryptAlgorithmEnum.SYM_AES.getValue().equals(symmetricAlgorithm)
                || EncryptAlgorithmEnum.SYM_SM4.getValue().equals(symmetricAlgorithm)) {
                return genSymmetricKeyBase64(EncryptAlgorithmEnum.SYM_AES.getValue(), 128, null);
            } else if (EncryptAlgorithmEnum.SYM_DES.getValue().equals(symmetricAlgorithm)
                || EncryptAlgorithmEnum.SYM_3DES.getValue().equals(symmetricAlgorithm)) {
                return genSymmetricKeyBase64(EncryptAlgorithmEnum.SYM_DES.getValue(), 56, null);
            } else {
                throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, MessageFormat.format("随机生成初始向量失败：对称加密算法[{0}]暂不支持", symmetricAlgorithm));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new BaseException(RspCodeStdEnum.ERROR_SYS_CFG, MessageFormat.format("随机生成初始向量失败：{0}", e.getMessage()));
        }
    }

    public static String genSymmetricKeyBase64(final String algorithm, final int bits, final String seed) throws NoSuchAlgorithmException {
        SecretKey key = genSymmetricKey(algorithm, bits, seed);
        return Base64.encode(key.getEncoded());
    }


    public static SecretKey genSymmetricKey(final String algorithm, final int bits, final String seed) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        if (StringUtils.isNotBlank(seed)) {
            random.setSeed(seed.getBytes());
        }

        random.nextBytes(new byte[8]);
        keyGen.init(bits, random);
        return keyGen.generateKey();
    }
}
