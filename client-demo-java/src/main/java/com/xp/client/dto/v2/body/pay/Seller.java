package com.xp.client.dto.v2.body.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 卖方
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    public static final String TYPE = "type";
    public static final String SN = "sn";
    public static final String NAME = "name";

    private String type; // 卖方类型
    private String sn; // 卖方编号
    private String name; // 卖方姓名
}
