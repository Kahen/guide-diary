
package me.zhengjie.modules.blog.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-16
 **/
@Entity
@Data
@Table(name = "guide_tips")
public class Tips implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "自增id")
    private Long id;

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
