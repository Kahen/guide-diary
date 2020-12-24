
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
public class RepostQueryCriteria {

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String blogId;

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String userId;

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String content;

    /**
     * 精确
     */
    @Query
    private Timestamp createTime;
}
