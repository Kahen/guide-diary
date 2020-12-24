
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-16
 **/
@Data
public class TipsDto implements Serializable {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 提示类型
     */
    private String tipType;

    /**
     * 提示时间段
     */
    private String periodType;
}
