package com.xp.client.dto.v2.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ray
 * @CreateTime: 2022-07-28 15:43
 * @Description: 安全报文体
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecretKey {

    protected String encryptKey;
    protected String encryptIV;
}
