
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-09
 **/
@Data
public class ImgQueryCriteria {

    /**
     * 精确
     */
    @Query
    private String imgId;

    /**
     * 精确
     */
    @Query
    private String blogId;

    /**
     * 模糊
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String imgUrl;
}
