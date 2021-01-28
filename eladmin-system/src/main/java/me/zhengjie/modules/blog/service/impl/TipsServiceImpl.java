/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
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
 * @date 2020-12-27
 **/
@Service
@RequiredArgsConstructor
public class TipsServiceImpl implements TipsService {

    private final TipsRepository tipsRepository;
    private final TipsMapper tipsMapper;

    @Override
    public Map<String, Object> queryAll(TipsQueryCriteria criteria, Pageable pageable) {
        Page<Tips> page =
                tipsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,
                        criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(tipsMapper::toDto));
    }

    @Override
    public List<TipsDto> queryAll(TipsQueryCriteria criteria) {
        return tipsMapper.toDto(tipsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public TipsDto findById(String id) {
        Tips tips = tipsRepository.findById(id).orElseGet(Tips::new);
        ValidationUtil.isNull(tips.getId(), "Tips", "id", id);
        return tipsMapper.toDto(tips);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TipsDto create(Tips resources) {
        resources.setId(IdUtil.simpleUUID());
        return tipsMapper.toDto(tipsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Tips resources) {
        Tips tips = tipsRepository.findById(resources.getId()).orElseGet(Tips::new);
        ValidationUtil.isNull(tips.getId(), "Tips", "id", resources.getId());
        tips.copy(resources);
        tipsRepository.save(tips);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
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

    @Override
    public List<Tips> buildRamDomTips(String period) {
        List<Tips> tipsByPeriodType = tipsRepository.findTipsByPeriodType(period);
        int r = (int) (System.currentTimeMillis() % (tipsByPeriodType.size() - 6));

        return tipsByPeriodType.subList(r, r + 6);
    }
}
