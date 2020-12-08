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
import lombok.experimental.Accessors;
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
@Table(name = "blog_comment")
@Accessors(chain = true)
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "评论ID")
    private Long id;

    @Column(name = "blog_id")
    @ApiModelProperty(value = "博客ID")
    private Long blogId;

    @Column(name = "user_id")
    @ApiModelProperty(value = "评论用户ID")
    private Long userId;

    @Column(name = "pid")
    @ApiModelProperty(value = "该评论所在的父级ID")
    private Long pid;

    @Column(name = "reply_id")
    @ApiModelProperty(value = "该评论回复的评论ID,没有则为0")
    private Long replyId;

    @Column(name = "content")
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "publish_time")
    @CreationTimestamp
    @ApiModelProperty(value = "发布时间")
    private Timestamp publishTime;

    public void copy(Comment source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
