
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-09
 **/
@Data
public class LikeDto implements Serializable {

    /**
     * 点赞ID
     */
    private String likeId;

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 点赞用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
