
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.Mood;
import me.zhengjie.modules.blog.service.dto.MoodDto;
import me.zhengjie.modules.blog.service.dto.MoodQueryCriteria;
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
public interface MoodService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map
            <String, Object> queryAll(MoodQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <MoodDto>
     */
    List
            <MoodDto> queryAll(MoodQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return MoodDto
     */
    MoodDto findById(String id);

    /**
     * 创建
     *
     * @param resources /
     * @return MoodDto
     */
    MoodDto create(Mood resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Mood resources);

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
                          <MoodDto> all, HttpServletResponse response) throws IOException;
}
