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
public class DiaryUserDto implements Serializable {

    /**
     * uid
     */
    private String uid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户介绍
     */
    private String description;

    /**
     * 粉丝数
     */
    private Long followersCount;

    /**
     * 关注数
     */
    private Long friendsCount;

    /**
     * 博客数
     */
    private Long statusesCount;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 密码哈希
     */
    private String password;
}
