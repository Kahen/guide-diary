
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.Diary;
import me.zhengjie.modules.blog.service.dto.DiaryDto;
import me.zhengjie.modules.blog.service.dto.DiaryQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @description 服务接口
 * @date 2021-01-11
 **/
public interface DiaryService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map<String, Object> queryAll(DiaryQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <DiaryDto>
     */
    List<DiaryDto> queryAll(DiaryQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return DiaryDto
     */
    DiaryDto findById(String id);

    /**
     * 创建
     *
     * @param resources /
     * @return DiaryDto
     */
    DiaryDto create(Diary resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Diary resources);

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
    void download(List<DiaryDto> all, HttpServletResponse response) throws IOException;

    /**
     * 根据userid 和时间查
     *
     * @param userId   /
     * @param dateTime /
     * @return /
     */
    DiaryDto findDiaryByUserIdAndDayTimestamp(String userId, String dateTime);

    /**
     * findDiaryByUserIdOrderByCreatedDate
     *
     * @param userId /
     * @return /
     */
    Page<DiaryDto> findDiaryByUser(String userId, Pageable pageable);
}
