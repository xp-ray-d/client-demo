package com.xp.client.dto.v2.body.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221201
 * @desc: 场景
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    public static final String IP = "ip";
    public static final String DEVICE = "device";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String URL = "url";

    private String ip; // 用户/终端IP
    private String device; // 设备编号
    private String type; // 场景类型
    private String name; // 场景名称
    private String code; // 场景代码
    private String url; // 场景地址
}
