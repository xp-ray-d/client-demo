package com.xp.client.dto.v2.body.innet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221205
 * @desc: 联系人
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnetContact {

    public static final String NAME = "name";
    public static final String CERT_NO = "certNo";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";

    private String name; // 姓名
    private String certNo; // 证件号码
    private String mobile; // 手机号
    private String email; // 电子邮件
}
