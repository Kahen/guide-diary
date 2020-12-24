
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-09
 **/
@Data
public class ImgDto implements Serializable {

    /**
     * 图片ID
     */
    private String imgId;

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 图片URL
     */
    private String imgUrl;
}
