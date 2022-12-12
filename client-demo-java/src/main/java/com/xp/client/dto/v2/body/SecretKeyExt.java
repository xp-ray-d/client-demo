package com.xp.client.dto.v2.body;

import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:43
 * @Description: 安全报文体（扩展，方便加解密操作）
 * @Version: 1.0
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecretKeyExt extends SecretKey {

    protected String encryptKeyPlain;
    protected String encryptIVPlain;

    protected String encryptAlgorithm;
    protected String encryptMode;
    protected String encryptPadding;

    public SecretKeyExt(SecretKey secretKey) {
        this.encryptKey = secretKey.getEncryptKey();
        this.encryptIV = secretKey.getEncryptIV();
    }

    public SecretKey getSecretKey() {
        return new SecretKey(this.getEncryptKey(), this.getEncryptIV());
    }

    public void encrypt(final XpKeystoreDto keystore) {
        final String keyCipher = SecurityUtil.encrypt(this.getEncryptKeyPlain(), keystore);
        final String ivCipher = SecurityUtil.encrypt(this.getEncryptIVPlain(), keystore);
        this.setEncryptKey(keyCipher);
        this.setEncryptIV(ivCipher);
    }

    public void decrypt(final XpKeystoreDto keystore) {
        final String keyPlain = SecurityUtil.decrypt(this.getEncryptKey(), keystore);
        final String ivPlain = SecurityUtil.decrypt(this.getEncryptIV(), keystore);
        this.setEncryptKeyPlain(keyPlain);
        this.setEncryptIVPlain(ivPlain);
    }
}
