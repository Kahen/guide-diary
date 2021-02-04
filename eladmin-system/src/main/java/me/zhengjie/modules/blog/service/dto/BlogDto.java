
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 *
 * @description /
 * @date 2020-12-09
 **/
@Data
public class BlogDto implements Serializable {

    /**
     * 博客ID
     */
    private String blogId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 视频URL
     */
    private String videoUrl;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    /**
     * 是否原创
     */
    private String isOriginal;

    private DiaryUserDto diaryUserDto;

    private ImgDto imgDto;

    private LikeDto like;

    private CollectDto collect;
}
