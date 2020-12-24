
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

@Table(name = "comment_like")
public class CommentLike implements Serializable {

    @Id
    @Column(name = "like_id")
    @ApiModelProperty(value = "点赞ID")
    private String likeId;

    @Column(name = "comment_id", nullable = false)
    @ApiModelProperty(value = "评论ID")
    private String commentId;

    @Column(name = "user_id", nullable = false)
    @ApiModelProperty(value = "点赞用户ID")
    private String userId;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    public void copy(CommentLike source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
