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

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Motto;
import me.zhengjie.modules.blog.repository.MottoRepository;
import me.zhengjie.modules.blog.service.MottoService;
import me.zhengjie.modules.blog.service.dto.MottoDto;
import me.zhengjie.modules.blog.service.dto.MottoQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.MottoMapper;
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
public class MottoServiceImpl implements MottoService {

    private final MottoRepository mottoRepository;
    private final MottoMapper mottoMapper;

    @Override
    public Map
            <String, Object> queryAll(MottoQueryCriteria criteria, Pageable pageable) {
        Page<Motto> page = mottoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(mottoMapper::toDto));
    }

    @Override
    public List
            <MottoDto> queryAll(MottoQueryCriteria criteria) {
        return mottoMapper.toDto(mottoRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public MottoDto findById(Long id) {
        Motto motto = mottoRepository.findById(id).orElseGet(Motto
                ::new);
        ValidationUtil.isNull(motto.getId(), "Motto", "id
                ",id);
        return mottoMapper.toDto(motto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MottoDto create(Motto resources) {
        return mottoMapper.toDto(mottoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Motto resources) {
        Motto motto = mottoRepository.findById(resources.getId
                ()).orElseGet(Motto::new);
        ValidationUtil.isNull(motto.getId(), "Motto
                ","id",resources.getId());
                motto.copy(resources);
        mottoRepository.save(motto);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            mottoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List
                                 <MottoDto> all, HttpServletResponse response) throws IOException {
        List
                <Map
                        <String
                                , Object>> list = new ArrayList<>();
        for (MottoDto motto : all) {
            Map
                    <String
                            , Object> map = new LinkedHashMap<>();
            map.put("作者", motto.getAuthor());
            map.put("内容", motto.getContent());
            map.put("格言分类", motto.getType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
