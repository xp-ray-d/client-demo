package com.xp.client.dto.v2.body.pay;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 订单
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    public static final String SUBJECT = "subject";
    public static final String SN = "sn";
    public static final String AMT = "amt";
    public static final String SELLER = "seller";
    public static final String STORE = "store";
    public static final String GOODS = "goods";
    public static final String DISCOUNT = "discount";
    public static final String TMS = "tms";

    private String subject; // 订单标题
    private String sn; // 订单编号
    private BigDecimal amt; // 订单金额
    private Seller seller; // 卖家信息
    private Store store; // 门店信息
    private List<Goods> listGoods; // 商品信息
    private List<Discount> listDiscount; // 优惠信息
    private Seller tms; // 物流信息
}
