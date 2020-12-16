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
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-16
 **/
@Entity
@Data
@Table(name = "diary")
public class Diary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "自增id")
    private Long id;

    @Column(name = "mood")
    @ApiModelProperty(value = "心情贴纸")
    private String mood;

    @Column(name = "tag")
    @ApiModelProperty(value = "标签")
    private String tag;

    @Column(name = "guide_1", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词1")
    private String guide1;

    @Column(name = "guide_2", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词2")
    private String guide2;

    @Column(name = "guide_3", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词3")
    private String guide3;

    @Column(name = "guide_4", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词4")
    private String guide4;

    @Column(name = "guide_5", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词5")
    private String guide5;

    @Column(name = "guide_6", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词6")
    private String guide6;

    @Column(name = "content_1")
    @ApiModelProperty(value = "内容1")
    private String content1;

    @Column(name = "content_2")
    @ApiModelProperty(value = "内容2")
    private String content2;

    @Column(name = "content_3")
    @ApiModelProperty(value = "内容3")
    private String content3;

    @Column(name = "content_4")
    @ApiModelProperty(value = "内容4")
    private String content4;

    @Column(name = "content_5")
    @ApiModelProperty(value = "内容5")
    private String content5;

    @Column(name = "content_6")
    @ApiModelProperty(value = "内容6")
    private String content6;

    @Column(name = "created_date", nullable = false)
    @NotNull
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createdDate;

    @Column(name = "updated_date", nullable = false)
    @NotNull
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updatedDate;

    @Column(name = "period", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "时间类型")
    private String period;

    public void copy(Diary source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
