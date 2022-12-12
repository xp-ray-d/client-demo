package com.xp.client.dto.v2.body.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221210
 * @desc: 门店
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    public static final String SN = "sn";
    public static final String NAME = "name";
    public static final String AREA = "area";
    public static final String ADDR = "addr";

    private String sn; // 门店编号
    private String name; // 门店名称
    private String area; // 门店地区编码
    private String addr; // 门店地址
}
