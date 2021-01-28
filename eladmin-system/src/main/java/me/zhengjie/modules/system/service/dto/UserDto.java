
package me.zhengjie.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

/**
 * @author Kahen
 * @date 2018-11-23
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserDto implements Serializable {

    private Long id;

    private String uid;


    private Set<RoleSmallDto> roles;

    private Set<JobSmallDto> jobs;

    private DeptSmallDto dept;

    private Long deptId;

    private String username;

    private String nickName;

    private String description;

    private String createBy;

    private String updatedBy;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Long followersCount;

    private Long friendsCount;

    private Long statuesCount;


    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JsonIgnore
    private String password;

    private Boolean enabled;

    @JsonIgnore
    private Boolean isAdmin = false;

    private Date pwdResetTime;
}
