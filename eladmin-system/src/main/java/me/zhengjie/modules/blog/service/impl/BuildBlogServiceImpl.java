package me.zhengjie.modules.blog.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.blog.constant.BlogConstants;
import me.zhengjie.modules.blog.domain.Blog;
import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.repository.BlogRepository;
import me.zhengjie.modules.blog.repository.CommentRepository;
import me.zhengjie.modules.blog.repository.DiaryUserRepository;
import me.zhengjie.modules.blog.service.BuildBlogService;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.utils.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiaxing
 * @date 2020/12/15
 */
@Service(value = "buildBlogService")
@Slf4j
public class BuildBlogServiceImpl implements BuildBlogService {


    private static final String HOME_BASE_URL = "https://api.weibo.com/2/statuses/home_timeline.json?";
    private static final String PUBLIC_BASE_URL = "https://api.weibo.com/2/statuses/public_timeline.json?";
    private static final String COMMENT_URL = "https://api.weibo.com/2/comments/show.json?";


    private final DiaryUserService diaryUserService;
    private final BlogRepository blogRepository;
    private final DiaryUserRepository diaryUserRepository;
    private final CommentRepository commentRepository;

    public BuildBlogServiceImpl(DiaryUserService diaryUserService, BlogRepository blogRepository, DiaryUserRepository diaryUserRepository, CommentRepository commentRepository) {
        this.diaryUserService = diaryUserService;
        this.blogRepository = blogRepository;
        this.diaryUserRepository = diaryUserRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public void build() {
        String blogStr = HttpUtil.get(HOME_BASE_URL + BlogConstants.accessToken() + "&count=1");
        JSONObject blogsObject = JSON.parseObject(blogStr);

        if (blogsObject.containsKey("error")) {
            log.info(blogsObject.getString("error"));
            return;
        }
        List<Blog> blogs = new ArrayList<>();
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
        ArrayList<Comment> comments = new ArrayList<>();
        for (Blog blog : blogs) {
            String s = HttpUtil.get(COMMENT_URL + BlogConstants.accessToken() + "&id=" + blog.getBlogId() + "&count=1");
            JSONObject jsonObject = JSON.parseObject(s);
            try {
                if (jsonObject.getInteger("total_number") == 0) {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONArray commentsArray = jsonObject.getJSONArray("comments");
            for (Object o : commentsArray) {

                JSONObject commentObject = (JSONObject) o;
                try {
                    comments.add(
                            new Comment().setCommentId(commentObject.getLong("id").toString())
                                    .setBlogId(blog.getBlogId())
                                    .setPublishTime(new Timestamp(DateFormatUtils.formatDate(commentObject.getString(
                                            "created_at"))))
                                    .setPid(commentObject.getLong("mid").toString())
                                    .setContent(commentObject.getString("text"))
                                    .setUserId(commentObject.getJSONObject("user").getLong("id").toString())
                                    .setReplyId(commentObject.getJSONObject("reply_comment") == null ? "0" : commentObject.getJSONObject("reply_comment").getLong("id").toString())
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DiaryUser user = diaryUserService.buildDiaryUser(commentObject.getJSONObject("user"));
                diaryUsers.add(user);
            }


        }
        blogRepository.saveAll(blogs);
        diaryUserRepository.saveAll(diaryUsers);
        commentRepository.saveAll(comments);

    }
}
