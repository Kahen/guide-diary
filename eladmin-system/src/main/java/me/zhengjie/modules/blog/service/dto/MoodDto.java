
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
public class MoodDto implements Serializable {

    /**
     * uuid
     */
    private String id;

    /**
     * 内容
     */
    private String text;
}
