
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Collect;
import me.zhengjie.modules.blog.repository.CollectRepository;
import me.zhengjie.modules.blog.service.CollectService;
import me.zhengjie.modules.blog.service.dto.CollectDto;
import me.zhengjie.modules.blog.service.dto.CollectQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.CollectMapper;
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
 *
 * @description 服务实现
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService {

    private final CollectRepository collectRepository;
    private final CollectMapper collectMapper;

    @Override
    public Map<String, Object> queryAll(CollectQueryCriteria criteria, Pageable pageable) {
        Page<Collect> page = collectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(collectMapper::toDto));
    }

    @Override
    public List<CollectDto> queryAll(CollectQueryCriteria criteria) {
        return collectMapper.toDto(collectRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public CollectDto findById(String collectId) {
        Collect collect = collectRepository.findById(collectId).orElseGet(Collect
                ::new);
        ValidationUtil.isNull(collect.getCollectId(), "Collect", "collectId", collectId);
        return collectMapper.toDto(collect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CollectDto create(Collect resources) {
        resources.setCollectId(IdUtil.simpleUUID());
        return collectMapper.toDto(collectRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Collect resources) {
        Collect collect = collectRepository.findById(resources.getCollectId
                ()).orElseGet(Collect::new);
        ValidationUtil.isNull(collect.getCollectId(), "Collect", "id", resources.getCollectId());
        collect.copy(resources);
        collectRepository.save(collect);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String collectId : ids) {
            collectRepository.deleteById(collectId);
        }
    }

    @Override
    public void download(List<CollectDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CollectDto collect : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("博客ID", collect.getBlogId());
            map.put("收藏用户ID", collect.getUserId());
            map.put("创建时间", collect.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
