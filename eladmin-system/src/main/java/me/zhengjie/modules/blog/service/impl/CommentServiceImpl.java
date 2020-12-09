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
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.constant.BlogConstants;
import me.zhengjie.modules.blog.domain.Blog;
import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.repository.CommentRepository;
import me.zhengjie.modules.blog.service.CommentService;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.service.dto.CommentDto;
import me.zhengjie.modules.blog.service.dto.CommentQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.CommentMapper;
import me.zhengjie.modules.blog.utils.DateFormatUtils;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
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
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_URL = "https://api.weibo.com/2/comments/show.json?";

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final DiaryUserService diaryUserService;

    @Override
    public Map<String, Object> queryAll(CommentQueryCriteria criteria, Pageable pageable) {
        Page<Comment> page = commentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(commentMapper::toDto));
    }

    @Override
    public List
            <CommentDto> queryAll(CommentQueryCriteria criteria) {
        return commentMapper.toDto(commentRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public CommentDto findById(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseGet(Comment
                ::new);
        ValidationUtil.isNull(comment.getCommentId(), "Comment", "commentId", commentId);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentDto create(Comment resources) {
        resources.setCommentId(IdUtil.simpleUUID());
        return commentMapper.toDto(commentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Comment resources) {
        Comment comment = commentRepository.findById(resources.getCommentId
                ()).orElseGet(Comment::new);
        ValidationUtil.isNull(comment.getCommentId(), "Comment", "id", resources.getCommentId());
        comment.copy(resources);
        commentRepository.save(comment);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String commentId : ids) {
            commentRepository.deleteById(commentId);
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

    @Override
    @Async
    public void fetchAndBuildComments(List<Blog> blogs) {
        ArrayList<Comment> comments = new ArrayList<>();
        ArrayList<DiaryUser> diaryUsers = new ArrayList<>();
        for (Blog blog : blogs) {
            String s = HttpUtil.get(COMMENT_URL + BlogConstants.accessToken() + "&&id=" + blog.getBlogId());
            JSONObject jsonObject = JSON.parseObject(s);
            comments.add(
                    new Comment().setCommentId(jsonObject.getLong("id").toString())
                            .setBlogId(blog.getBlogId())
                            .setPublishTime(new Timestamp(DateFormatUtils.formatDate(jsonObject.getString("created_at"))))
                            .setPid(jsonObject.getLong("mid").toString())
                            .setContent(jsonObject.getString("text"))
                            .setUserId(jsonObject.getJSONObject("user").getLong("id").toString())
                            .setReplyId(jsonObject.getJSONObject("reply_comment") == null ? "0" : jsonObject.getJSONObject("reply_comment").getLong("id").toString())
            );
            DiaryUser user = diaryUserService.buildDiaryUser(jsonObject.getJSONObject("user"));
            diaryUsers.add(user);
        }
        commentRepository.saveAll(comments);
        diaryUserService.saveAll(diaryUsers);
    }
}
