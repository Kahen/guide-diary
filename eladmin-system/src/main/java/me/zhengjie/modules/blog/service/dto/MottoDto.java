
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
public class MottoDto implements Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容
     */
    private String content;

    /**
     * 格言分类
     */
    private String type;
}
