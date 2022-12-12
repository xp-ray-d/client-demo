package com.xp.client.dto.v2.body.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221201
 * @desc: 付款方
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payer {

    public static final String SN = "sn";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String ACCT = "acct";

    private String sn; // 付款方编号
    private String type; // 付款方类型
    private String name; // 付款方姓名
    private String acct; // 付款方账号
}
