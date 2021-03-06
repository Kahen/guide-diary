
package me.zhengjie.modules.mnt.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.mnt.domain.Database;
import me.zhengjie.modules.mnt.service.dto.DatabaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 * @date 2019-08-24
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DatabaseMapper extends BaseMapper<DatabaseDto, Database> {

}
