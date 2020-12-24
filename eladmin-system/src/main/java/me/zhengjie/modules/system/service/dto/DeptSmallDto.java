
package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Kahen
 * @date 2019-6-10 16:32:18
 */
@Data
@Accessors(chain = true)
public class DeptSmallDto implements Serializable {

    private Long id;

    private String name;
}
