
package me.zhengjie.modules.mnt.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.mnt.domain.DeployHistory;
import me.zhengjie.modules.mnt.service.dto.DeployHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 * @date 2019-08-24
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployHistoryMapper extends BaseMapper<DeployHistoryDto, DeployHistory> {

}
