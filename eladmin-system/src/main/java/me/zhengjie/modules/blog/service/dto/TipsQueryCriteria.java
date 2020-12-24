
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author Kahen
 *
 * @date 2020-12-16
 **/
@Data
public class TipsQueryCriteria {

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String content;

    /**
     * 精确
     */
    @Query
    private String tipType;

    /**
     * 精确
     */
    @Query
    private String periodType;
}
