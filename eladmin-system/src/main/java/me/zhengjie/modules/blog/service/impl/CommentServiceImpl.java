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
import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.repository.CommentRepository;
import me.zhengjie.modules.blog.service.CommentService;
import me.zhengjie.modules.blog.service.dto.CommentDto;
import me.zhengjie.modules.blog.service.dto.CommentQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.CommentMapper;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Map<String, Object> queryAll(CommentQueryCriteria criteria, Pageable pageable) {
        Page<Comment> page =
                commentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,
                        criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(commentMapper::toDto));
    }

    @Override
    public List<CommentDto> queryAll(CommentQueryCriteria criteria) {
        return commentMapper.toDto(commentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public CommentDto findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseGet(Comment::new);
        ValidationUtil.isNull(comment.getId(), "Comment", "id", id);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentDto create(Comment resources) {
        return commentMapper.toDto(commentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Comment resources) {
        Comment comment = commentRepository.findById(resources.getId()).orElseGet(Comment::new);
        ValidationUtil.isNull(comment.getId(), "Comment", "id", resources.getId());
        comment.copy(resources);
        commentRepository.save(comment);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            commentRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CommentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CommentDto comment : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("博客ID", comment.getBlogId());
            map.put("评论用户ID", comment.getUserId());
            map.put("该评论所在的父级ID", comment.getPid());
            map.put("该评论回复的评论ID,没有则为0", comment.getReplyId());
            map.put("内容", comment.getContent());
            map.put("发布时间", comment.getPublishTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}