
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.sql.Timestamp;

/**
 * @author Kahen
 *
 * @date 2020-12-09
 **/
@Data
public class CommentLikeQueryCriteria {

    /**
     * 精确
     */
    @Query
    private String likeId;

    /**
     * 精确
     */
    @Query
    private String commentId;

    /**
     * 精确
     */
    @Query
    private String userId;

    /**
     * 精确
     */
    @Query
    private Timestamp createTime;
}
