
package me.zhengjie.modules.blog.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @description /
 * @date 2021-01-08
 **/
@Data
public class DiaryDto implements Serializable {

    /**
     * uuid
     */
    private String id;

    /**
     * 心情贴纸
     */
    private String mood;

    /**
     * 标签
     */
    private String tag;

    /**
     * 引导词1
     */
    private String guide1;

    /**
     * 引导词2
     */
    private String guide2;

    /**
     * 引导词3
     */
    private String guide3;

    /**
     * 引导词4
     */
    private String guide4;

    /**
     * 引导词5
     */
    private String guide5;

    /**
     * 引导词6
     */
    private String guide6;

    /**
     * 内容1
     */
    private String content1;

    /**
     * 内容2
     */
    private String content2;

    /**
     * 内容3
     */
    private String content3;

    /**
     * 内容4
     */
    private String content4;

    /**
     * 内容5
     */
    private String content5;

    /**
     * 内容6
     */
    private String content6;

    /**
     * 创建时间
     */
    private Timestamp createdDate;

    /**
     * 更新时间
     */
    private Timestamp updatedDate;

    /**
     * 时间类型
     */
    private String period;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 时间索引
     */
    private Long dayTimestamp;
}
