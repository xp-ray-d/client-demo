package com.xp.client.dto.v2.body.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221201
 * @desc: 业务信息
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BsnInfo {

    public static final String SUBJECT = "subject";
    public static final String SN = "sn";
    public static final String AMT = "amt";
    public static final String EXPIRE = "expire";

    private String subject; // 订单标题
    private String sn; // 订单编号
    private String amt; // 订单金额
    private String expire; // 订单付款超时时间
}
