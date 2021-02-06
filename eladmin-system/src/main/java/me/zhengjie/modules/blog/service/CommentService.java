
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.service.dto.CommentDto;
import me.zhengjie.modules.blog.service.dto.CommentQueryCriteria;
import org.springframework.data.domain.Page;
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
public interface CommentService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map<String, Object> queryAll(CommentQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <CommentDto>
     */
    List<CommentDto> queryAll(CommentQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param commentId ID
     * @return CommentDto
     */
    CommentDto findById(String commentId);

    /**
     * 创建
     *
     * @param resources /
     * @return CommentDto
     */
    CommentDto create(Comment resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Comment resources);

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
    void download(List<CommentDto> all, HttpServletResponse response) throws IOException;


    Page<CommentDto> findCommentsByBlogId(String blogId, Pageable pageable);
}
