
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
 * @author Kahen
 *
 * @date 2020-12-09
 **/
@Data
public class DiaryUserQueryCriteria{

            /** 模糊 */
            @Query(type = Query.Type.INNER_LIKE)
            private String uid;

            /** 模糊 */
            @Query(type = Query.Type.INNER_LIKE)
            private String name;

            /** 模糊 */
            @Query(type = Query.Type.INNER_LIKE)
            private String nickname;
}
