
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
public class CollectDto implements Serializable {

    /**
     * 转发ID
     */
    private String collectId;

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 收藏用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
