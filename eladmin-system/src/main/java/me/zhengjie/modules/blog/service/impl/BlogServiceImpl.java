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
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.constant.BlogConstants;
import me.zhengjie.modules.blog.domain.Blog;
import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.repository.BlogRepository;
import me.zhengjie.modules.blog.service.BlogService;
import me.zhengjie.modules.blog.service.CommentService;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.service.dto.BlogDto;
import me.zhengjie.modules.blog.service.dto.BlogQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.BlogMapper;
import me.zhengjie.modules.blog.utils.DateFormatUtils;
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
import java.sql.Timestamp;
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
public class BlogServiceImpl implements BlogService {

    private static final String HOME_BASE_URL = "https://api.weibo.com/2/statuses/home_timeline.json?";


    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final DiaryUserService diaryUserService;
    private final CommentService commentService;

    @Override
    public Map
            <String, Object> queryAll(BlogQueryCriteria criteria, Pageable pageable) {
        Page<Blog> page = blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(blogMapper::toDto));
    }

    @Override
    public List
            <BlogDto> queryAll(BlogQueryCriteria criteria) {
        return blogMapper.toDto(blogRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public BlogDto findById(String blogId) {
        Blog blog = blogRepository.findById(blogId).orElseGet(Blog
                ::new);
        ValidationUtil.isNull(blog.getBlogId(), "Blog", "blogId", blogId);
        return blogMapper.toDto(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogDto create(Blog resources) {
        resources.setBlogId(IdUtil.simpleUUID());
        return blogMapper.toDto(blogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog resources) {
        Blog blog = blogRepository.findById(resources.getBlogId
                ()).orElseGet(Blog::new);
        ValidationUtil.isNull(blog.getBlogId(), "Blog", "id", resources.getBlogId());
        blog.copy(resources);
        blogRepository.save(blog);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String blogId : ids) {
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
        String blogStr = HttpUtil.get(HOME_BASE_URL + BlogConstants.accessToken());
        // todo: build blog and build user and comment
        // todo add comment fetch
        JSONObject blogsObject = JSON.parseObject(blogStr);
        List<Blog> blogs = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<DiaryUser> diaryUsers = new ArrayList<>();
        JSONArray statuses = blogsObject.getJSONArray("statuses");
        for (Object status : statuses) {
            JSONObject statusObject = (JSONObject) status;
            blogs.add(
                    new Blog()
                            .setBlogId(statusObject.getLong("id").toString())
                            .setContent(statusObject.getString("text"))
                            .setCreateTime(new Timestamp(System.currentTimeMillis()))
                            .setPublishTime(new Timestamp(DateFormatUtils.formatDate(statusObject.getString("created_at"))))
                            .setUserId(statusObject.getJSONObject("user").getLong("id").toString())
                            .setIsOriginal("是"));


            JSONObject userObject = statusObject.getJSONObject("user");
            diaryUsers.add(diaryUserService.buildDiaryUser(userObject));
        }
        commentService.fetchAndBuildComments(blogs);
        blogRepository.saveAll(blogs);
        diaryUserService.saveAll(diaryUsers);
    }

}
