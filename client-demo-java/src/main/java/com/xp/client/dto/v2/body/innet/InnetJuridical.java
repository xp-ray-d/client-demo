package com.xp.client.dto.v2.body.innet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221205
 * @desc: 法人
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnetJuridical {

    public static final String JURIDICAL_NAME = "juridicalName";
    public static final String JURIDICAL_CERT_TYPE = "juridicalCertType";
    public static final String JURIDICAL_CERT_NO = "juridicalCertNo";
    public static final String JURIDICAL_CERT_FRONT = "juridicalCertFront";
    public static final String JURIDICAL_CERT_BACK = "juridicalCertBack";

    private String juridicalName; // 法人姓名
    private String juridicalCertType; // 法人证件类型
    private String juridicalCertNo; // 法人证件号码
    private String juridicalCertFront; // 法人证件非人像面照片
    private String juridicalCertBack; // 法人证件人像面照片
}
