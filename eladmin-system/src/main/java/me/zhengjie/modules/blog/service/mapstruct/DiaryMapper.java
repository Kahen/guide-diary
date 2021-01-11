
package me.zhengjie.modules.blog.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.blog.domain.Diary;
import me.zhengjie.modules.blog.service.dto.DiaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Kahen
 * @date 2021-01-11
 **/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiaryMapper extends BaseMapper
        <DiaryDto, Diary> {

}
