
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.sql.Timestamp;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-09
 **/
@Data
public class BlogQueryCriteria {

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String content;

    /**
     * 精确
     */
    @Query
    private String videoUrl;

    /**
     * 精确
     */
    @Query
    private Timestamp createTime;

    /**
     * 精确
     */
    @Query
    private Timestamp publishTime;

    /**
     * 精确
     */
    @Query
    private String isOriginal;
}
