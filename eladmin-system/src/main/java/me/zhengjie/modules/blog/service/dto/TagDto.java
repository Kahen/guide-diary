
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description /
 * @date 2020-12-16
 **/
@Data
public class TagDto implements Serializable {

    /**
     * uuid
     */
    private String id;

    /**
     * 内容
     */
    private String text;
}
