
package me.zhengjie.modules.system.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.service.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 * @date 2019-5-23
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {

}
