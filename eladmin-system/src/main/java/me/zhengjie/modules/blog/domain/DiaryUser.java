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
package me.zhengjie.modules.blog.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-09
 **/
@Entity
@Data
@Table(name = "diary_user")
public class DiaryUser implements Serializable {

    @Id
    @Column(name = "uid")
    @ApiModelProperty(value = "uid")
    private String uid;

    @Column(name = "name", nullable = false)
    @ApiModelProperty(value = "用户名")
    private String name;

    @Column(name = "nickname", nullable = false)
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @Column(name = "description", nullable = false)
    @ApiModelProperty(value = "用户介绍")
    private String description;

    @Column(name = "followers_count", nullable = false)
    @ApiModelProperty(value = "粉丝数")
    private Long followersCount;

    @Column(name = "friends_count", nullable = false)
    @ApiModelProperty(value = "关注数")
    private Long friendsCount;

    @Column(name = "statuses_count", nullable = false)
    @ApiModelProperty(value = "博客数")
    private Long statusesCount;

    @Column(name = "create_time", nullable = false)
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "avatar_url", nullable = false)
    @ApiModelProperty(value = "头像链接")
    private String avatarUrl;

    @Column(name = "password")
    @ApiModelProperty(value = "密码哈希")
    private String password;

    public void copy(DiaryUser source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
