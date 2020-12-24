
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.CommentLike;
import me.zhengjie.modules.blog.service.dto.CommentLikeDto;
import me.zhengjie.modules.blog.service.dto.CommentLikeQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 *
 * @description 服务接口
 * @date 2020-12-09
 **/
public interface CommentLikeService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map<String, Object> queryAll(CommentLikeQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <CommentLikeDto>
     */
    List<CommentLikeDto> queryAll(CommentLikeQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param likeId ID
     * @return CommentLikeDto
     */
    CommentLikeDto findById(String likeId);

    /**
     * 创建
     *
     * @param resources /
     * @return CommentLikeDto
     */
    CommentLikeDto create(CommentLike resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(CommentLike resources);

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
    void download(List<CommentLikeDto> all, HttpServletResponse response) throws IOException;
}
