package com.xp.client.dto.v2.body.pay;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 优惠
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    public static final String SN = "sn";
    public static final String TYPE = "type";
    public static final String DESC = "desc";
    public static final String AMT = "amt";

    private String sn; // 优惠编号
    private String type; // 优惠类型
    private String desc; // 优惠描述
    private BigDecimal amt; // 优惠金额
}
