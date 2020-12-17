
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.Repost;
import me.zhengjie.modules.blog.service.dto.RepostDto;
import me.zhengjie.modules.blog.service.dto.RepostQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-12-09
 **/
public interface RepostService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map
            <String, Object> queryAll(RepostQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <RepostDto>
     */
    List
            <RepostDto> queryAll(RepostQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param repostId ID
     * @return RepostDto
     */
    RepostDto findById(String repostId);

    /**
     * 创建
     *
     * @param resources /
     * @return RepostDto
     */
    RepostDto create(Repost resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Repost resources);

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
                          <RepostDto> all, HttpServletResponse response) throws IOException;
}
