
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
public class DiaryUserDto implements Serializable {

    /**
     * uid
     */
    private String uid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户介绍
     */
    private String description;

    /**
     * 粉丝数
     */
    private Long followersCount;

    /**
     * 关注数
     */
    private Long friendsCount;

    /**
     * 博客数
     */
    private Long statusesCount;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 密码哈希
     */
    private String password;
}
