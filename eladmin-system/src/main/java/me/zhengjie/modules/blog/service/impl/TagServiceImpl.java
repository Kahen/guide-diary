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
import me.zhengjie.modules.blog.domain.Tag;
import me.zhengjie.modules.blog.repository.TagRepository;
import me.zhengjie.modules.blog.service.TagService;
import me.zhengjie.modules.blog.service.dto.TagDto;
import me.zhengjie.modules.blog.service.dto.TagQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.TagMapper;
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
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Map<String, Object> queryAll(TagQueryCriteria criteria, Pageable pageable) {
        Page<Tag> page = tagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,
                criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(tagMapper::toDto));
    }

    @Override
    public List<TagDto> queryAll(TagQueryCriteria criteria) {
        return tagMapper.toDto(tagRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public TagDto findById(String id) {
        Tag tag = tagRepository.findById(id).orElseGet(Tag::new);
        ValidationUtil.isNull(tag.getId(), "Tag", "id", id);
        return tagMapper.toDto(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TagDto create(Tag resources) {
        resources.setId(IdUtil.simpleUUID());
        return tagMapper.toDto(tagRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Tag resources) {
        Tag tag = tagRepository.findById(resources.getId()).orElseGet(Tag::new);
        ValidationUtil.isNull(tag.getId(), "Tag", "id", resources.getId());
        tag.copy(resources);
        tagRepository.save(tag);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            tagRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TagDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TagDto tag : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("内容", tag.getText());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}