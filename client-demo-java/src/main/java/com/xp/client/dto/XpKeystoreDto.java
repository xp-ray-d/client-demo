package com.xp.client.dto;

import java.util.Date;
import lombok.Data;

@Data
public class XpKeystoreDto {

    private Long id;
    private Date createDate;
    private String createUser;
    private String createOrg;
    private Date updateDate;
    private String updateUser;
    private String updateOrg;
    private Integer updateNo;
    private Boolean deleted;
    private Integer status;
    private Integer sortNo;
    private String entityId;
    private String tenantId;
    private String memo;
    private String objType;
    private Long objId;
    private String objCode;
    private String appId;
    private String charset;
    private String keyType;
    private String keyName;
    private String keyValue;
    private String keyUrl;
    private String keyPsw;
    private String keyAlgorithmEncrypt;
    private String keyPadding;
    private String keyMode;
    private String keyIv;
    private String keyAlgorithmHash;
    private String keyAlgorithmSign;
    private String keyAlgorithmEncode;
    private String keyParam;
}
