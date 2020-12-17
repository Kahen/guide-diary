
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-16
 **/
@Data
public class MoodQueryCriteria {

    /**
     * 精确
     */
    @Query
    private String id;

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String text;
}
