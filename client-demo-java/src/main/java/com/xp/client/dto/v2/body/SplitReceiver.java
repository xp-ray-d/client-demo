package com.xp.client.dto.v2.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Author: ray
 * @CreateTime: 20221001
 * @Description: 分账方
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SplitReceiver {

    public static final String TYPE = "type";
    public static final String ACCT = "acct";
    public static final String NAME = "name";
    public static final String MEMO = "memo";
    public static final String AMT = "amt";

    public static final String SN = "sn";
    public static final String RISK = "risk";

    private String type; // 分账方类型
    private String acct; // 分账方账号
    private String name; // 分账方名称
    private String memo; // 分账关系描述/分账备注
    private BigDecimal amt; // 分账金额

    private String sn; // 分账方编码
    private String risk; // 分账风控
    private String bind; // 绑定结果
    private String state; // 分账结果

    public SplitReceiver(final String type, final String acct) {
        this.type = type;
        this.acct = acct;
    }

    public SplitReceiver(final String type, final String acct, final String name, final String memo) {
        this.type = type;
        this.acct = acct;
        this.name = name;
        this.memo = memo;
    }

    public SplitReceiver(final String type, final String acct, final String name, final BigDecimal amt, final String memo) {
        this.type = type;
        this.acct = acct;
        this.name = name;
        this.amt = amt;
        this.memo = memo;
    }
}
