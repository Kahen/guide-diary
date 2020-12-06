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

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Blog;
import me.zhengjie.modules.blog.repository.BlogRepository;
import me.zhengjie.modules.blog.service.BlogService;
import me.zhengjie.modules.blog.service.dto.BlogDto;
import me.zhengjie.modules.blog.service.dto.BlogQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.BlogMapper;
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
 * @date 2020-12-05
 **/
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private static final String BASE_URL = "https://api.weibo.com/2/statuses";
    private static final String ACCESS_TOKEN_URL = "access_token=2.00CcTx1ClhslKB466ae0c66enFS86C";
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    @Override
    public Map<String, Object> queryAll(BlogQueryCriteria criteria, Pageable pageable) {
        Page<Blog> page =
                blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,
                        criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(blogMapper::toDto));
    }

    @Override
    public List<BlogDto> queryAll(BlogQueryCriteria criteria) {
        return blogMapper.toDto(blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public BlogDto findById(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElseGet(Blog::new);
        ValidationUtil.isNull(blog.getBlogId(), "Blog", "blogId", blogId);
        return blogMapper.toDto(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogDto create(Blog resources) {
        return blogMapper.toDto(blogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog resources) {
        Blog blog = blogRepository.findById(resources.getBlogId()).orElseGet(Blog::new);
        ValidationUtil.isNull(blog.getBlogId(), "Blog", "id", resources.getBlogId());
        blog.copy(resources);
        blogRepository.save(blog);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long blogId : ids) {
            blogRepository.deleteById(blogId);
        }
    }

    @Override
    public void download(List<BlogDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BlogDto blog : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户ID", blog.getUserId());
            map.put("内容", blog.getContent());
            map.put("视频URL", blog.getVideoUrl());
            map.put("创建时间", blog.getCreateTime());
            map.put("发布时间", blog.getPublishTime());
            map.put("是否原创", blog.getIsOriginal());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void buildBlog() {
        String blogStr = HttpUtil.get(BASE_URL + "/public_timeline.json?" + ACCESS_TOKEN_URL);
        JSONObject jsonObject = JSON.parseObject(blogStr);
        List<Blog> blogs = new ArrayList<>();

    }
}