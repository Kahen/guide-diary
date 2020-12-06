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
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-05
 **/
@Entity
@Data
@Table(name = "blog_repost")
public class Repost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repost_id")
    @ApiModelProperty(value = "转发ID")
    private Long repostId;

    @Column(name = "blog_id")
    @ApiModelProperty(value = "博客ID")
    private Long blogId;

    @Column(name = "user_id")
    @ApiModelProperty(value = "转发用户ID")
    private Long userId;

    @Column(name = "content")
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    public void copy(Repost source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}