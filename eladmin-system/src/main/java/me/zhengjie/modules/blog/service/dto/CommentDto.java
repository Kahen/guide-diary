
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-09
 **/
@Data
public class CommentDto implements Serializable {

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 评论用户ID
     */
    private String userId;

    /**
     * 该评论所在的父级ID
     */
    private String pid;

    /**
     * 该评论回复的评论ID,没有则为0
     */
    private String replyId;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    /**
     * 评论ID
     */
    private String commentId;
}
