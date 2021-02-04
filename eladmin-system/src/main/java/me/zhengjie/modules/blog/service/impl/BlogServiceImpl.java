
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.*;
import me.zhengjie.modules.blog.repository.*;
import me.zhengjie.modules.blog.service.BlogService;
import me.zhengjie.modules.blog.service.dto.BlogDto;
import me.zhengjie.modules.blog.service.dto.BlogQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.*;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.utils.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
 * @description 服务实现
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {


    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final DiaryUserRepository diaryUserRepository;
    private final DiaryUserMapper diaryUserMapper;
    private final ImgRepository imgRepository;
    private final ImgMapper imgMapper;
    private final LikeRepository likeRepository;
    private final CollectRepository collectRepository;
    private final LikeMapper likeMapper;
    private final CollectMapper collectMapper;

    @Override
    public Map<String, Object> queryAll(BlogQueryCriteria criteria, Pageable pageable) {
        Page<Blog> page =
                blogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,
                        criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(blogMapper::toDto));
    }

    @Override
    public List<BlogDto> queryAll(BlogQueryCriteria criteria) {
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
    public Page<BlogDto> homeLine(Pageable pageable) {
        Page<Blog> blogs = blogRepository.findAll(pageable);
        List<BlogDto> blogDtos = new ArrayList<>();
        for (Blog blog : blogs.getContent()) {
            DiaryUser diaryUser = diaryUserRepository.findById(blog.getUserId()).orElse(null);
            BlogDto blogDto = blogMapper.toDto(blog);
            blogDto.setDiaryUserDto(diaryUserMapper.toDto(diaryUser));
            Img imgByBlogId = imgRepository.findImgByBlogId(blog.getBlogId());
            if (imgByBlogId != null) {
                blogDto.setImgDto(imgMapper.toDto(imgByBlogId));
            }
            JwtUserDto currentUser = (JwtUserDto) SecurityUtils.getCurrentUser();
            Like likeByBlogIdAndUserId = likeRepository.findLikeByBlogIdAndUserId(blog.getBlogId(), currentUser.getUser().getUid());
            if (likeByBlogIdAndUserId != null) {
                blogDto.setLike(likeMapper.toDto(likeByBlogIdAndUserId));
            }
            Collect collectByBlogIdAndUserId = collectRepository.findCollectByBlogIdAndUserId(blog.getBlogId(), currentUser.getUser().getUid());
            if (collectByBlogIdAndUserId != null) {
                blogDto.setCollect(collectMapper.toDto(collectByBlogIdAndUserId));
            }
            blogDtos.add(blogDto);
        }
        return new PageImpl<>(blogDtos, pageable, blogs.getTotalElements());
    }


}
