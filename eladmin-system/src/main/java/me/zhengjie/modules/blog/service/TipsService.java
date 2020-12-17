
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.Tips;
import me.zhengjie.modules.blog.service.dto.TipsDto;
import me.zhengjie.modules.blog.service.dto.TipsQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-12-16
 **/
public interface TipsService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map
            <String, Object> queryAll(TipsQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <TipsDto>
     */
    List
            <TipsDto> queryAll(TipsQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return TipsDto
     */
    TipsDto findById(Long id);

    /**
     * 创建
     *
     * @param resources /
     * @return TipsDto
     */
    TipsDto create(Tips resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Tips resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List
                          <TipsDto> all, HttpServletResponse response) throws IOException;
}
