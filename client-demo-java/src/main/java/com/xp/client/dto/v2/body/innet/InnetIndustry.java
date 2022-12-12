package com.xp.client.dto.v2.body.innet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221205
 * @desc: 行业信息
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InnetIndustry {

    public static final String INDUSTRY_CATEGORY = "industryCategory";
    public static final String INDUSTRY_TYPE = "industryType";
    public static final String QUALIFICATION_PHOTO = "qualificationPhoto";

    private String industryCategory; // 行业大类
    private String industryType; // 行业小类
    private String qualificationPhoto; // 行业资质证照
}
