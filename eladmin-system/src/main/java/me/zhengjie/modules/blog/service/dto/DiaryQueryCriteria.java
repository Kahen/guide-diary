
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author Kahen
 * @date 2021-01-08
 **/
@Data
public class DiaryQueryCriteria {

    /**
     * 精确
     */
    @Query
    private String id;

    /**
     * 精确
     */
    @Query
    private Long dayTimestamp;
}
