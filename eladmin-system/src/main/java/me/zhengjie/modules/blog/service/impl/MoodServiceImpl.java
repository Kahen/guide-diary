
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Mood;
import me.zhengjie.modules.blog.repository.MoodRepository;
import me.zhengjie.modules.blog.service.MoodService;
import me.zhengjie.modules.blog.service.dto.MoodDto;
import me.zhengjie.modules.blog.service.dto.MoodQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.MoodMapper;
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
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;
    private final MoodMapper moodMapper;

    @Override
    public Map
            <String, Object> queryAll(MoodQueryCriteria criteria, Pageable pageable) {
        Page<Mood> page = moodRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(moodMapper::toDto));
    }

    @Override
    public List
            <MoodDto> queryAll(MoodQueryCriteria criteria) {
        return moodMapper.toDto(moodRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public MoodDto findById(String id) {
        Mood mood = moodRepository.findById(id).orElseGet(Mood
                ::new);
        ValidationUtil.isNull(mood.getId(), "Mood", "id", id);
        return moodMapper.toDto(mood);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MoodDto create(Mood resources) {
        resources.setId(IdUtil.simpleUUID());
        return moodMapper.toDto(moodRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Mood resources) {
        Mood mood = moodRepository.findById(resources.getId
                ()).orElseGet(Mood::new);
        ValidationUtil.isNull(mood.getId(), "Mood", "id", resources.getId());
        mood.copy(resources);
        moodRepository.save(mood);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            moodRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<MoodDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MoodDto mood : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("内容", mood.getText());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
