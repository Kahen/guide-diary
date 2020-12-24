
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author Kahen
 *
 * @date 2020-12-16
 **/
@Data
public class DiaryQueryCriteria {

    /**
     * 精确
     */
    @Query
    private Long id;
}
