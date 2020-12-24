
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.Tag;
import me.zhengjie.modules.blog.service.dto.TagDto;
import me.zhengjie.modules.blog.service.dto.TagQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 *
 * @description 服务接口
 * @date 2020-12-16
 **/
public interface TagService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map
            <String, Object> queryAll(TagQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <TagDto>
     */
    List
            <TagDto> queryAll(TagQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return TagDto
     */
    TagDto findById(String id);

    /**
     * 创建
     *
     * @param resources /
     * @return TagDto
     */
    TagDto create(Tag resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Tag resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(String[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List
                          <TagDto> all, HttpServletResponse response) throws IOException;
}
