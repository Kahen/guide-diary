
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.repository.CommentRepository;
import me.zhengjie.modules.blog.repository.DiaryUserRepository;
import me.zhengjie.modules.blog.service.CommentService;
import me.zhengjie.modules.blog.service.dto.CommentDto;
import me.zhengjie.modules.blog.service.dto.CommentQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.CommentMapper;
import me.zhengjie.modules.blog.service.mapstruct.DiaryUserMapper;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.utils.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
 *
 * @description 服务实现
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_URL = "https://api.weibo.com/2/comments/show.json?";

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final DiaryUserRepository diaryUserRepository;
    private final DiaryUserMapper diaryUserMapper;

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
    public Page<CommentDto> findCommentsByBlogId(String blogId, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findCommentsByBlogId(blogId, pageable);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : commentPage.getContent()) {
            CommentDto commentDto = commentMapper.toDto(comment);
            DiaryUser diaryUser = diaryUserRepository.findById(comment.getUserId()).orElse(null);
            commentDto.setDiaryUserDto(diaryUserMapper.toDto(diaryUser));
            commentDtos.add(commentDto);
        }
        return new PageImpl<>(commentDtos, pageable, commentPage.getTotalElements());
    }


    @Override
    public CommentDto addCommentByBlogId(String blogId, String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);
        String simpleUuid = IdUtil.simpleUUID();
        JwtUserDto currentUser = (JwtUserDto) SecurityUtils.getCurrentUser();
        Comment save = commentRepository.save(new Comment()
                .setCommentId(simpleUuid)
                .setContent(jsonObject.getString("commentArea"))
                .setBlogId(blogId)
                .setPid(simpleUuid)
                .setUserId(currentUser.getUser().getUid())
                .setReplyId("0")
                .setPublishTime(new Timestamp(System.currentTimeMillis())));
        CommentDto commentDto = commentMapper.toDto(save);
        commentDto.setDiaryUserDto(diaryUserMapper.toDto(diaryUserRepository.findById(save.getUserId()).orElse(null)));
        return commentDto;
    }
}
