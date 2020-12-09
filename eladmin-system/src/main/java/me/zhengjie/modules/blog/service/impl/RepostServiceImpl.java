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
import me.zhengjie.modules.blog.domain.Repost;
import me.zhengjie.modules.blog.repository.RepostRepository;
import me.zhengjie.modules.blog.service.RepostService;
import me.zhengjie.modules.blog.service.dto.RepostDto;
import me.zhengjie.modules.blog.service.dto.RepostQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.RepostMapper;
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
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class RepostServiceImpl implements RepostService {

    private final RepostRepository repostRepository;
    private final RepostMapper repostMapper;

    @Override
    public Map<String, Object> queryAll(RepostQueryCriteria criteria, Pageable pageable) {
        Page<Repost> page = repostRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(repostMapper::toDto));
    }

    @Override
    public List<RepostDto> queryAll(RepostQueryCriteria criteria) {
        return repostMapper.toDto(repostRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public RepostDto findById(String repostId) {
        Repost repost = repostRepository.findById(repostId).orElseGet(Repost
                ::new);
        ValidationUtil.isNull(repost.getRepostId(), "Repost", "repostId", repostId);
        return repostMapper.toDto(repost);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RepostDto create(Repost resources) {
        resources.setRepostId(IdUtil.simpleUUID());
        return repostMapper.toDto(repostRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Repost resources) {
        Repost repost = repostRepository.findById(resources.getRepostId
                ()).orElseGet(Repost::new);
        ValidationUtil.isNull(repost.getRepostId(), "Repost", "id", resources.getRepostId());
        repost.copy(resources);
        repostRepository.save(repost);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String repostId : ids) {
            repostRepository.deleteById(repostId);
        }
    }

    @Override
    public void download(List<RepostDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RepostDto repost : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("博客ID", repost.getBlogId());
            map.put("转发用户ID", repost.getUserId());
            map.put("内容", repost.getContent());
            map.put("创建时间", repost.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
