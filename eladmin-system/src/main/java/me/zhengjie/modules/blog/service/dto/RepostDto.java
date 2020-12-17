
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
public class RepostDto implements Serializable {

    /**
     * 转发ID
     */
    private String repostId;

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 转发用户ID
     */
    private String userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
