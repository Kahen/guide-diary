
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.system.service.dto.DeptSmallDto;
import me.zhengjie.modules.system.service.dto.UserDto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 *
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


    public static UserDto toUserDto(DiaryUser user) {
        if (user == null) {
            return null;
        }

        return new UserDto().setEnabled(true)
                .setAvatarPath(user.getAvatarUrl())
                .setEmail("null")
                .setUid(user.getUid())
                .setUsername(user.getName())
                .setPassword(user.getPassword())
                .setDept(new DeptSmallDto().setId(System.currentTimeMillis()).setName("用户")).setCreateTime(user.getCreateTime())
                .setNickName(user.getNickname())
                .setDescription(user.getDescription())
                .setFollowersCount(user.getFollowersCount())
                .setFriendsCount(user.getFriendsCount())
                .setStatuesCount(user.getStatusesCount())
                .setAvatarPath(user.getAvatarUrl());

    }
}
