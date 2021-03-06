
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.CommentLike;
import me.zhengjie.modules.blog.repository.CommentLikeRepository;
import me.zhengjie.modules.blog.service.CommentLikeService;
import me.zhengjie.modules.blog.service.dto.CommentLikeDto;
import me.zhengjie.modules.blog.service.dto.CommentLikeQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.CommentLikeMapper;
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
 *
 * @description 服务实现
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentLikeMapper commentLikeMapper;

    @Override
    public Map<String, Object> queryAll(CommentLikeQueryCriteria criteria, Pageable pageable) {
        Page<CommentLike> page = commentLikeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(commentLikeMapper::toDto));
    }

    @Override
    public List<CommentLikeDto> queryAll(CommentLikeQueryCriteria criteria) {
        return commentLikeMapper.toDto(commentLikeRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public CommentLikeDto findById(String likeId) {
        CommentLike commentLike = commentLikeRepository.findById(likeId).orElseGet(CommentLike
                ::new);
        ValidationUtil.isNull(commentLike.getLikeId(), "CommentLike", "likeId", likeId);
        return commentLikeMapper.toDto(commentLike);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentLikeDto create(CommentLike resources) {
        resources.setLikeId(IdUtil.simpleUUID());
        return commentLikeMapper.toDto(commentLikeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CommentLike resources) {
        CommentLike commentLike = commentLikeRepository.findById(resources.getLikeId
                ()).orElseGet(CommentLike::new);
        ValidationUtil.isNull(commentLike.getLikeId(), "CommentLike", "id", resources.getLikeId());
        commentLike.copy(resources);
        commentLikeRepository.save(commentLike);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String likeId : ids) {
            commentLikeRepository.deleteById(likeId);
        }
    }

    @Override
    public void download(List<CommentLikeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CommentLikeDto commentLike : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("评论ID", commentLike.getCommentId());
            map.put("点赞用户ID", commentLike.getUserId());
            map.put("创建时间", commentLike.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
