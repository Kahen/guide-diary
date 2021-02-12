
package me.zhengjie.modules.blog.service;

import com.alibaba.fastjson.JSONObject;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.service.dto.DiaryUserDto;
import me.zhengjie.modules.blog.service.dto.DiaryUserQueryCriteria;
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
public interface DiaryUserService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map<String, Object> queryAll(DiaryUserQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <DiaryUserDto>
     */
    List<DiaryUserDto> queryAll(DiaryUserQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param uid ID
     * @return DiaryUserDto
     */
    DiaryUserDto findById(String uid);

    /**
     * 创建
     *
     * @param resources /
     * @return DiaryUserDto
     */
    DiaryUserDto create(DiaryUser resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(DiaryUser resources);

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
    void download(List<DiaryUserDto> all, HttpServletResponse response) throws IOException;

    public DiaryUser buildDiaryUser(JSONObject userObject);

    void saveAll(List<DiaryUser> diaryUsers);

    DiaryUser findByEmail(String email);
}
