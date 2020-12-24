
package me.zhengjie.modules.blog.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.blog.domain.Img;
import me.zhengjie.modules.blog.service.dto.ImgDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 * @date 2020-12-09
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImgMapper extends BaseMapper<ImgDto, Img> {

}
