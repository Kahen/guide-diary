
package me.zhengjie.modules.blog.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Kahen
 * @description /
 * @date 2021-01-11
 **/
@Entity
@Data
@Accessors(chain = true)
@Table(name = "diary")
public class Diary implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "uuid")
    private String id;

    @Column(name = "mood")
    @ApiModelProperty(value = "心情贴纸")
    private String mood;

    @Column(name = "tag")
    @ApiModelProperty(value = "标签")
    private String tag;

    @Column(name = "guide_1", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词1")
    private String guide1;

    @Column(name = "guide_2", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词2")
    private String guide2;

    @Column(name = "guide_3", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词3")
    private String guide3;

    @Column(name = "guide_4", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词4")
    private String guide4;

    @Column(name = "guide_5", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词5")
    private String guide5;

    @Column(name = "guide_6", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "引导词6")
    private String guide6;

    @Column(name = "content_1")
    @ApiModelProperty(value = "内容1")
    private String content1;

    @Column(name = "content_2")
    @ApiModelProperty(value = "内容2")
    private String content2;

    @Column(name = "content_3")
    @ApiModelProperty(value = "内容3")
    private String content3;

    @Column(name = "content_4")
    @ApiModelProperty(value = "内容4")
    private String content4;

    @Column(name = "content_5")
    @ApiModelProperty(value = "内容5")
    private String content5;

    @Column(name = "content_6")
    @ApiModelProperty(value = "内容6")
    private String content6;

    @Column(name = "created_date", nullable = false)
//    @NotNull
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createdDate;

    @Column(name = "updated_date", nullable = false)
//    @NotNull
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间")
    private Timestamp updatedDate;

    @Column(name = "period", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "时间类型")
    private String period;

    @Column(name = "user_id", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "用户id")
    private String userId;

    @Column(name = "day_timestamp", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "时间索引")
    private String dayTimestamp;

    public void copy(Diary source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
