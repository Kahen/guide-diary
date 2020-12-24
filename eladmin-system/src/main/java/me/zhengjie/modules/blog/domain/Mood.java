
package me.zhengjie.modules.blog.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-16
 **/
@Entity
@Data
@Table(name = "guide_mood")
public class Mood implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "uuid")
    private String id;

    @Column(name = "text", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "内容")
    private String text;

    public void copy(Mood source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
