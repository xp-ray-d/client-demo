package com.xp.client.dto.v2.body.innet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221205
 * @desc: 业务主体
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnetOwner {

    public static final String CIT_TYPE = "cifType";
    public static final String ENT_CREDIT_CODE = "entCreditCode";
    public static final String ENT_CERT_PHOTO = "entCertPhoto";
    public static final String CIF_NAME = "cifName";
    public static final String CIF_BRIEF_NAME = "cifBriefName";
    public static final String TP_BANK_PERMIT = "tpBankPermit";
    public static final String TP_BANK_PERMIT_PHOTO = "tpBankPermitPhoto";
    public static final String ENT_ENT_CERT_IN_HAND_PHOTO = "entCertInHandPhoto";
    public static final String ENT_SERVICE_PHONE = "entServicePhone";

    private String cifType; // 主体类型
    private String entCreditCode; // 证件号码
    private String entCertPhoto; // 证件照片
    private String cifName; // 主体名称
    private String cifBriefName; // 主体简称
    private String tpBankPermit; // 开户许可证编号
    private String tpBankPermitPhoto; // 开户许可证照片
    private String entCertInHandPhoto; // 手持营业执照在经营场所的照片
    private String entServicePhone; // 客服电话
}
