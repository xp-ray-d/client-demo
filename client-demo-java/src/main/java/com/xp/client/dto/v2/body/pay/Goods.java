package com.xp.client.dto.v2.body.pay;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 商品
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    public static final String SN = "sn";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String CNT = "cnt";
    public static final String AMT = "amt";
    public static final String URL = "url";

    private String sn; // 商品编号
    private String type; // 商品类目
    private String name; // 商品名称
    private Integer cnt; // 商品数量
    private BigDecimal amt; // 商品金额
    private String url; // 商品展示网址
}
