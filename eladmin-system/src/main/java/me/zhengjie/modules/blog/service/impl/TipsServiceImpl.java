
package me.zhengjie.modules.blog.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Tips;
import me.zhengjie.modules.blog.repository.TipsRepository;
import me.zhengjie.modules.blog.service.TipsService;
import me.zhengjie.modules.blog.service.dto.TipsDto;
import me.zhengjie.modules.blog.service.dto.TipsQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.TipsMapper;
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
public class TipsServiceImpl implements TipsService {

    private final TipsRepository tipsRepository;
    private final TipsMapper tipsMapper;

    @Override
    public Map
            <String, Object> queryAll(TipsQueryCriteria criteria, Pageable pageable) {
        Page<Tips> page = tipsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(tipsMapper::toDto));
    }

    @Override
    public List
            <TipsDto> queryAll(TipsQueryCriteria criteria) {
        return tipsMapper.toDto(tipsRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public TipsDto findById(Long id) {
        Tips tips = tipsRepository.findById(id).orElseGet(Tips
                ::new);
        ValidationUtil.isNull(tips.getId(), "Tips", "id", id);
        return tipsMapper.toDto(tips);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TipsDto create(Tips resources) {
        return tipsMapper.toDto(tipsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Tips resources) {
        Tips tips = tipsRepository.findById(resources.getId
                ()).orElseGet(Tips::new);
        ValidationUtil.isNull(tips.getId(), "Tips", "id", resources.getId());
        tips.copy(resources);
        tipsRepository.save(tips);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            tipsRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TipsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TipsDto tips : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("内容", tips.getContent());
            map.put("提示类型", tips.getTipType());
            map.put("提示时间段", tips.getPeriodType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
