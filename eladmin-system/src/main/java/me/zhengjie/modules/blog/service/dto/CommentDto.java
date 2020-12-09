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
 * @date 2020-12-09
 **/
@Data
public class CommentDto implements Serializable {

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 评论用户ID
     */
    private String userId;

    /**
     * 该评论所在的父级ID
     */
    private String pid;

    /**
     * 该评论回复的评论ID,没有则为0
     */
    private String replyId;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    /**
     * 评论ID
     */
    private String commentId;
}
