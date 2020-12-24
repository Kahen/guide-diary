
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
import java.io.Serializable;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-09
 **/
@Entity
@Data
@Accessors(chain = true)
@Table(name = "blog_img")
public class Img implements Serializable {

    @Id
    @Column(name = "img_id")
    @ApiModelProperty(value = "图片ID")
    private String imgId;

    @Column(name = "blog_id")
    @ApiModelProperty(value = "博客ID")
    private String blogId;

    @Column(name = "img_url")
    @ApiModelProperty(value = "图片URL")
    private String imgUrl;

    public void copy(Img source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
