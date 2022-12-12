package com.xp.client.dto.v2.body.pay;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 结算
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sett {

    public static final String EXPIRE = "expire";
    public static final String TYPE = "type";
    public static final String ACCT = "acct";
    public static final String AMT = "amt";
    public static final String DESC = "desc";

    private String expire; // 订单自动结算日期
    private String type; // 账号类型
    private String acct; // 账号
    private BigDecimal amt; // 结算金额
    private String desc; // 结算附言
}
