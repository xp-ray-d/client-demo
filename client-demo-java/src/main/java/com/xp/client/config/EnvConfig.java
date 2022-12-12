package com.xp.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "simulator")
@Data
public class EnvConfig {

    private String baseUrl;
    private String cusCode;
    private String cusPrivateKey;
    private String platPublicKey;
}
