
package me.zhengjie.modules.blog.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.blog.domain.Collect;
import me.zhengjie.modules.blog.service.dto.CollectDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 *
 * @date 2020-12-09
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectMapper extends BaseMapper
        <CollectDto, Collect> {

}
