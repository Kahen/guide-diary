/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-05
 **/
@Data
public class BlogDto implements Serializable {

    /**
     * 博客ID
     */
    private Long blogId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 视频URL
     */
    private String videoUrl;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    /**
     * 是否原创
     */
    private String isOriginal;
}