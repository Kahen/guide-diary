
package me.zhengjie.modules.mnt.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.mnt.domain.ServerDeploy;
import me.zhengjie.modules.mnt.service.dto.ServerDeployDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 * @date 2019-08-24
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServerDeployMapper extends BaseMapper<ServerDeployDto, ServerDeploy> {

}
