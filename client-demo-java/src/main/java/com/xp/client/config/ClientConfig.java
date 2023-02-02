package com.xp.client.config;

import com.xp.client.dto.XpKeystoreDto;
import com.xp.client.enums.EncryptAlgorithmEnum;
import com.xp.client.enums.EncryptKeyTypeEnum;
import com.xp.client.enums.SignAlgorithmEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author: gzb
 * @date: 20221027
 * @desc: 客户参数配置
 * @version: 1.0
 */
@Data
public class ClientConfig implements Serializable {

    public static final String CUS_CODE = "C0000035C0001";
    public static final String SYM_KEY = "0123456789ABCDEF";
    public static final String PLAT_PUBLIC = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr7kN3GycZjK0McKi350AZsn8Djm23S2Z+OOrDxaa9DhEStT1aBJjS1DRCi/O4f30mGOq/isi/FXR14CXvTepP4AAcyxvLUn9R4N18Pyzo/CDqXEVXOtNvYe5/tNUkpQ0ZDl+tMN91xH5unNmAgGeTcQnl0quzD7dPl/+8P8BT50ovid3Osnh6iVa5I9mqXhpF+PUUnvjAZQGwMBtjL0HQdnNWmNwVA8UtnwQCFPjt9JmLHm4+bvZqLCUsbagfD3jTZF9Ula4FXARwzEZ+mHHXllEGGy8JOohqZBREeAwUn5Us1sUtCrhIxkYdm48USW/tdZjWzljjd/bkUu5Jj5IDQIDAQAB";
    public static final String CUS_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCvDKUmSFB8KwRsefsnZbEupoHQp+JXT8hVK6wQXZp4xNiJUS4I+jJj5BgG9WTzJ0CR+EwO84apsgFrXciDi3PwDyLcB5ebaeTnK55o/mF3WqiJIpfAvkDl3szyA2rl5SPpq6KNp8ikMEsvsTpU8SFk2QMrUy35nu5pmIDUk4nGyJMQ88FVilgn2qEmYYcbIQG7e8WKzf6gHbJBQ7Mzxnb5yEZ3JD4f/LOo6keKwubeDXYaIb06HRLJASHyN3RlTyGWaJx2BIMbs6xgkJaGLUX6r4E9UqorD5X0LAkfDgOA8jpOYht0IQMuJtCCb1qCd23M1l7G+D9po/d3ln1P7cyPAgMBAAECggEBAImAzVm9BDwFLnxndToFT6DtepuExO8A5MTCgW55mJW1SsLS2e6DWoHM8nLE4H+AHeCLwnMcKHO0OBk2mgN6z5+KxD9CV3zlOcG9Kbof2EABHtJaMgpdaG0pDqQsuxPLYuJyyEWtvpX6uZTFCqUepLSrIyotNLgYd/7KpKhXHKrgJFzu3atIFkaK/Ti/Bt+CL4YSXU14EHWdCu4MKj+CvoXhlDzLxA1geDQUQ/BjmnqCHHU/XnlCpBfUGMbD7Ci/eExxjmomcwbeIMDvfvhFO9luX7aE8E1GgQoroS3VXM+cinZlsWrfiHlw1yHGY5TS15Q+Ld6UcCZpM9t2o1LMoMECgYEA7w5C6Ak47w/Jfs0GNPRgSOVC5ccLHzC1AALsOKRxjnx95DRbVATXivk0qNB3hdLSZ3TFXnj7hdPQxhbyom2hmMVVJvW8mwInem04FBc/AH3cQTK2BWYgJO0SuoSHE6GwK+EAQmbMTsswNBAO1zYaB89tUsyqjLXA8lZMy5pW2oUCgYEAu3T4Ps/t8lmpcUJDBAako/MidbSeATjXAqDjJ0bnHk/bxzJvKTwuj0M9hEn9q3JIv1zbsfnlIl5LDME6TZaNBuhW6nFq+BDei/FvWFg73dI6HzJU15mocDBeq8ROUD1+8/UUUBQMpFly43w3MSve1By68sWUjpDpS1zUa+x9WQMCgYEAqbqDcIHTxZB87KWe6Yw0rKzyQXiAAgYwDLEGjPTcl2D2dUdjLJU/gDGN3OoeYoqbzs6Or5PQaWVJjsuOPBsAqBo7ai0BW0E9TGQtc75JggzajAP4Tn8DBZS5PmqICT08evsGlx6roqoLMHvSPaTw+Gii4y3jTYJiWu1BGzIaxo0CgYBr9/QfoX1oj/IDNOoKgJUFhT181BoJlCZq8iXDcpgdoltwwQC7nFko1M6yyjccL4ftCFvVh5xNDkhlwUmGdMcnily9TbQRyw2TRu0d0jwxCCvH2iJ5x5v3ejSc9EUy/CiJ9MfNU67wXkDI+7uCS8VdIGsg92IYNyjX6lDDFIQh5wKBgGDmxuK7xxUlPuz99w83stIscNxD4qOeOg5b7QtWwPEcUedcKJhcdhDxBO3jWepI5imwUL49bDe61u7gbo2YsTZE69PS/Rd/sYIpBkzH/jOOePTTWKGhiGwfkdPHcfjcfw0Ch5wP32BLce9N2D8gpB/hEipxRxH3tAMgWAFHks9Q";

    private String baseUrl;
    private String cusCode;
    private List<XpKeystoreDto> listKeyDto;

    public static ClientConfig getDefault() {
        return getTest();
    }

    public static ClientConfig getTest() {
        final String baseUrl = "http://test-open-cap.kuaijie-pay.com:48847/forward/pay";
        final ClientConfig clientConfig = new ClientConfig(baseUrl, CUS_CODE, CUS_PRIVATE, PLAT_PUBLIC);
        return clientConfig;
    }

    public static ClientConfig getProduct() {
        final String baseUrl = "https://api.kuaijie-pay.com/forward/pay";
        final ClientConfig clientConfig = new ClientConfig(baseUrl, CUS_CODE, CUS_PRIVATE, PLAT_PUBLIC);
        return clientConfig;
    }

    private ClientConfig(final String baseUrl, final String cusCode, final String cusPrivateKey, final String platPublicKey) {
        this.baseUrl = baseUrl;
        this.cusCode = cusCode;

        this.listKeyDto = new ArrayList<>();
        // 客户私钥
        final XpKeystoreDto cusPrivateKeyDto = ClientConfig.getKeystoreDto(EncryptKeyTypeEnum.ASYM_PRIVATE, cusPrivateKey);
        this.listKeyDto.add(cusPrivateKeyDto);
        // 平台公钥
        final XpKeystoreDto platPublicKeyDto = ClientConfig.getKeystoreDto(EncryptKeyTypeEnum.ASYM_PUBLIC_PLAT, platPublicKey);
        this.listKeyDto.add(platPublicKeyDto);
    }

    public static XpKeystoreDto getKeystoreDto(final EncryptKeyTypeEnum keyTypeEnum, final String keyValue) {
        final XpKeystoreDto keyDto = new XpKeystoreDto();
        keyDto.setKeyType(keyTypeEnum.getValue());
        keyDto.setKeyAlgorithmEncrypt(EncryptAlgorithmEnum.ASYM_RSA.getValue());
        keyDto.setKeyAlgorithmSign(SignAlgorithmEnum.SHA256withRSA.getValue());
        keyDto.setKeyValue(keyValue);
        keyDto.setKeyValue(keyDto.getKeyValue());
        return keyDto;
    }
}
