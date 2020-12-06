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
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.repository.DiaryUserRepository;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.service.dto.DiaryUserDto;
import me.zhengjie.modules.blog.service.dto.DiaryUserQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.DiaryUserMapper;
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
 * @date 2020-12-06
 **/
@Service
@RequiredArgsConstructor
public class DiaryUserServiceImpl implements DiaryUserService {

    private final DiaryUserRepository diaryUserRepository;
    private final DiaryUserMapper diaryUserMapper;

    @Override
    public Map
            <String, Object> queryAll(DiaryUserQueryCriteria criteria, Pageable pageable) {
        Page<DiaryUser> page =
                diaryUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,
                        criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(diaryUserMapper::toDto));
    }

    @Override
    public List
            <DiaryUserDto> queryAll(DiaryUserQueryCriteria criteria) {
        return diaryUserMapper.toDto(diaryUserRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public DiaryUserDto findById(Long id) {
        DiaryUser diaryUser = diaryUserRepository.findById(id).orElseGet(DiaryUser
                ::new);
        ValidationUtil.isNull(diaryUser.getId(), "DiaryUser", "id
                ",id);
        return diaryUserMapper.toDto(diaryUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DiaryUserDto create(DiaryUser resources) {
        return diaryUserMapper.toDto(diaryUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DiaryUser resources) {
        DiaryUser diaryUser = diaryUserRepository.findById(resources.getId
                ()).orElseGet(DiaryUser::new);
        ValidationUtil.isNull(diaryUser.getId(), "DiaryUser
                ","id",resources.getId());
                diaryUser.copy(resources);
        diaryUserRepository.save(diaryUser);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            diaryUserRepository.deleteById(id);
        }
    }

    @Override
    public void download(List
                                 <DiaryUserDto> all, HttpServletResponse response) throws IOException {
        List
                <Map
                        <String
                                , Object>> list = new ArrayList<>();
        for (DiaryUserDto diaryUser : all) {
            Map
                    <String
                            , Object> map = new LinkedHashMap<>();
            map.put("uid", diaryUser.getUid());
            map.put("用户名", diaryUser.getName());
            map.put("昵称", diaryUser.getNickname());
            map.put("用户介绍", diaryUser.getDescription());
            map.put("粉丝数", diaryUser.getFollowersCount());
            map.put("关注数", diaryUser.getFriendsCount());
            map.put("博客数", diaryUser.getStatusesCount());
            map.put("创建时间", diaryUser.getCreateTime());
            map.put("头像链接", diaryUser.getAvatarUrl());
            map.put("密码哈希", diaryUser.getPassword());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}