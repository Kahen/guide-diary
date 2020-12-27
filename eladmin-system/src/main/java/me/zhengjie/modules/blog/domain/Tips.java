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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-27
 **/
@Entity
@Data
@Accessors(chain = true)
@Table(name = "guide_tips")
public class Tips implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "uuid")
    private String id;

    @Column(name = "content", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "tip_type", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "提示类型")
    private String tipType;

    @Column(name = "period_type", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "提示时间段")
    private String periodType;

    public void copy(Tips source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}