
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Like;
import me.zhengjie.modules.blog.repository.LikeRepository;
import me.zhengjie.modules.blog.service.LikeService;
import me.zhengjie.modules.blog.service.dto.LikeDto;
import me.zhengjie.modules.blog.service.dto.LikeQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.LikeMapper;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.utils.*;
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
 *
 * @description 服务实现
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    @Override
    public Map<String, Object> queryAll(LikeQueryCriteria criteria, Pageable pageable) {
        Page<Like> page = likeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(likeMapper::toDto));
    }

    @Override
    public List<LikeDto> queryAll(LikeQueryCriteria criteria) {
        return likeMapper.toDto(likeRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public LikeDto findById(String likeId) {
        Like like = likeRepository.findById(likeId).orElseGet(Like::new);
        ValidationUtil.isNull(like.getLikeId(), "Like", "likeId ", likeId);
        return likeMapper.toDto(like);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LikeDto create(Like resources) {
        resources.setLikeId(IdUtil.simpleUUID());
        return likeMapper.toDto(likeRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Like resources) {
        Like like = likeRepository.findById(resources.getLikeId
                ()).orElseGet(Like::new);
        ValidationUtil.isNull(like.getLikeId(), "Like", "id", resources.getLikeId());
        like.copy(resources);
        likeRepository.save(like);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String likeId : ids) {
            likeRepository.deleteById(likeId);
        }
    }

    @Override
    public void download(List<LikeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LikeDto like : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("博客ID", like.getBlogId());
            map.put("点赞用户ID", like.getUserId());
            map.put("创建时间", like.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public LikeDto createOrUpdate(JwtUserDto currentUser, String likeId, String blogId) {
        if (StringUtils.isBlank(likeId)) {
            Like like = new Like().setLikeId(IdUtil.simpleUUID())
                    .setBlogId(blogId)
                    .setUserId(currentUser.getUser().getUid())
                    .setCreateTime(new Timestamp(System.currentTimeMillis()));
            return likeMapper.toDto(likeRepository.save(like));
        }
        likeRepository.deleteById(likeId);
        return new LikeDto();
    }
}
