package com.xp.client.dto.v2.body.open;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ray
 * @date: 20221205
 * @desc: 分账场景
 * @version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenSplitScene {

    public static final String SPLIT_SCENE = "splitScene";
    public static final String SPLIT_SCENE_FILE = "splitSceneFile";

    private String splitScene; // 分账场景
    private String splitSceneFile; // 分账证明文件
}
