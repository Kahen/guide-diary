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
 * @date 2020-12-27
 **/
@Data
public class DiaryDto implements Serializable {

    /** 自增id */
    private String id;

    /** 心情贴纸 */
    private String mood;

    /** 标签 */
    private String tag;

    /** 引导词1 */
    private String guide1;

    /** 引导词2 */
    private String guide2;

    /** 引导词3 */
    private String guide3;

    /** 引导词4 */
    private String guide4;

    /** 引导词5 */
    private String guide5;

    /** 引导词6 */
    private String guide6;

    /** 内容1 */
    private String content1;

    /** 内容2 */
    private String content2;

    /** 内容3 */
    private String content3;

    /** 内容4 */
    private String content4;

    /** 内容5 */
    private String content5;

    /** 内容6 */
    private String content6;

    /**
     * 创建时间
     */
    private Timestamp createdDate;

    /**
     * 更新时间
     */
    private Timestamp updatedDate;

    /**
     * 时间类型
     */
    private String period;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 日记本id
     */
    private String bookId;
}