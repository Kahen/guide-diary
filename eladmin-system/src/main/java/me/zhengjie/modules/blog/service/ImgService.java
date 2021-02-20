
package me.zhengjie.modules.blog.service;

import com.alibaba.fastjson.JSONArray;
import me.zhengjie.modules.blog.domain.Img;
import me.zhengjie.modules.blog.service.dto.ImgDto;
import me.zhengjie.modules.blog.service.dto.ImgQueryCriteria;
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
public interface ImgService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map<String, Object> queryAll(ImgQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <ImgDto>
     */
    List<ImgDto> queryAll(ImgQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param imgId ID
     * @return ImgDto
     */
    ImgDto findById(String imgId);

    /**
     * 创建
     *
     * @param resources /
     * @return ImgDto
     */
    ImgDto create(Img resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Img resources);

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
    void download(List<ImgDto> all, HttpServletResponse response) throws IOException;

    ImgDto findByDiaryId(String diaryId);

    void fetchImages(String blogId, JSONArray picUrls);
}
