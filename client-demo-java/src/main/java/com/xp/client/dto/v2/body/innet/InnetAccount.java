package com.xp.client.dto.v2.body.innet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221205
 * @desc: 结算账户
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnetAccount {

    public static final String BANK_CODE = "bankCode";
    public static final String BANK_ACCT_TYPE = "bankAcctType";
    public static final String BANK_ACCT_NO = "bankAcctNo";
    public static final String BANK_ACCT_NAME = "bankAcctName";

    private String bankCode; // 银行代码
    private String bankAcctType; // 银行账户类型
    private String bankAcctNo; // 银行账号
    private String bankAcctName; // 银行账户名
}
