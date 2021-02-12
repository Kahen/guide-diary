package me.zhengjie.modules.blog.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kahen
 * @date 2021/2/11
 */
@Data
@Entity
@Table(name = "active_code")
@Accessors(chain = true)

public class ActiveCode {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "active_code")
    private String activeCode;

}
