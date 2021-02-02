
package me.zhengjie.modules.blog.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-09
 **/
@Entity
@Data
@Accessors(chain = true)
@Table(name = "blog")
public class Blog implements Serializable {

    @Id
    @Column(name = "blog_id")
    @ApiModelProperty(value = "博客ID")
    private String blogId;

    @Column(name = "user_id")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @Column(name = "content", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "video_url")
    @ApiModelProperty(value = "视频URL")
    private String videoUrl;

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "publish_time")
//    @UpdateTimestamp
    @ApiModelProperty(value = "发布时间")
    private Timestamp publishTime;

    @Column(name = "is_original")
    @ApiModelProperty(value = "是否原创")
    private String isOriginal;

    public void copy(Blog source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
