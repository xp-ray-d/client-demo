package com.xp.client.dto.v2.body.pay;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 物流
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tms {

    public static final String SN = "sn";
    public static final String TYPE = "type";
    public static final String AMT = "amt";
    public static final String PAYER = "payer";

    private String sn; // 物流单号
    private String type; // 物流类型
    private BigDecimal amt; // 运费
    private String payer; // 运费承担方
}
