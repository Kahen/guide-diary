
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
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-09
 **/
@Entity
@Data
@Accessors(chain = true)

@Table(name = "blog_comment")
public class Comment implements Serializable {

    @Column(name = "blog_id")
    @ApiModelProperty(value = "博客ID")
    private String blogId;

    @Column(name = "user_id")
    @ApiModelProperty(value = "评论用户ID")
    private String userId;

    @Column(name = "pid")
    @ApiModelProperty(value = "该评论所在的父级ID")
    private String pid;

    @Column(name = "reply_id")
    @ApiModelProperty(value = "该评论回复的评论ID,没有则为0")
    private String replyId;

    @Column(name = "content", nullable = false)
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "publish_time")
    @CreationTimestamp
    @ApiModelProperty(value = "发布时间")
    private Timestamp publishTime;

    @Id
    @Column(name = "comment_id")
    @ApiModelProperty(value = "评论ID")
    private String commentId;

    public void copy(Comment source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
