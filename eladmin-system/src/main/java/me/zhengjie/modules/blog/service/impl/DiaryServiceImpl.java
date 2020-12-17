
package me.zhengjie.modules.blog.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Diary;
import me.zhengjie.modules.blog.repository.DiaryRepository;
import me.zhengjie.modules.blog.service.DiaryService;
import me.zhengjie.modules.blog.service.dto.DiaryDto;
import me.zhengjie.modules.blog.service.dto.DiaryQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.DiaryMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description 服务实现
 * @date 2020-12-16
 **/
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryMapper diaryMapper;

    @Override
    public Map
            <String, Object> queryAll(DiaryQueryCriteria criteria, Pageable pageable) {
        Page<Diary> page = diaryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(diaryMapper::toDto));
    }

    @Override
    public List
            <DiaryDto> queryAll(DiaryQueryCriteria criteria) {
        return diaryMapper.toDto(diaryRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public DiaryDto findById(Long id) {
        Diary diary = diaryRepository.findById(id).orElseGet(Diary
                ::new);
        ValidationUtil.isNull(diary.getId(), "Diary", "id", id);
        return diaryMapper.toDto(diary);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DiaryDto create(Diary resources) {
        return diaryMapper.toDto(diaryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Diary resources) {
        Diary diary = diaryRepository.findById(resources.getId
                ()).orElseGet(Diary::new);
        ValidationUtil.isNull(diary.getId(), "Diary", "id", resources.getId());
        diary.copy(resources);
        diaryRepository.save(diary);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            diaryRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<DiaryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DiaryDto diary : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("心情贴纸", diary.getMood());
            map.put("标签", diary.getTag());
            map.put("引导词1", diary.getGuide1());
            map.put("引导词2", diary.getGuide2());
            map.put("引导词3", diary.getGuide3());
            map.put("引导词4", diary.getGuide4());
            map.put("引导词5", diary.getGuide5());
            map.put("引导词6", diary.getGuide6());
            map.put("内容1", diary.getContent1());
            map.put("内容2", diary.getContent2());
            map.put("内容3", diary.getContent3());
            map.put("内容4", diary.getContent4());
            map.put("内容5", diary.getContent5());
            map.put("内容6", diary.getContent6());
            map.put("创建时间", diary.getCreatedDate());
            map.put("更新时间", diary.getUpdatedDate());
            map.put("时间类型", diary.getPeriod());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
