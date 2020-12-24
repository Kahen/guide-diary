
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
public class CommentLikeDto implements Serializable {

    /**
     * 点赞ID
     */
    private String likeId;

    /**
     * 评论ID
     */
    private String commentId;

    /**
     * 点赞用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
