
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.Img;
import me.zhengjie.modules.blog.repository.ImgRepository;
import me.zhengjie.modules.blog.service.ImgService;
import me.zhengjie.modules.blog.service.dto.ImgDto;
import me.zhengjie.modules.blog.service.dto.ImgQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.ImgMapper;
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
public class ImgServiceImpl implements ImgService {

    private final ImgRepository imgRepository;
    private final ImgMapper imgMapper;

    @Override
    public Map
            <String, Object> queryAll(ImgQueryCriteria criteria, Pageable pageable) {
        Page<Img> page = imgRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(imgMapper::toDto));
    }

    @Override
    public List
            <ImgDto> queryAll(ImgQueryCriteria criteria) {
        return imgMapper.toDto(imgRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public ImgDto findById(String imgId) {
        Img img = imgRepository.findById(imgId).orElseGet(Img
                ::new);
        ValidationUtil.isNull(img.getImgId(), "Img", "imgId ", imgId);
        return imgMapper.toDto(img);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ImgDto create(Img resources) {
        if (resources.getImgId() == null) {
            resources.setImgId(IdUtil.simpleUUID());
        }
        return imgMapper.toDto(imgRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Img resources) {
        Img img = imgRepository.findById(resources.getImgId
                ()).orElseGet(Img::new);
        ValidationUtil.isNull(img.getImgId(), "Img", "id", resources.getImgId());
        img.copy(resources);
        imgRepository.save(img);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String imgId : ids) {
            imgRepository.deleteById(imgId);
        }
    }

    @Override
    public void download(List<ImgDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ImgDto img : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("博客ID", img.getBlogId());
            map.put("图片URL", img.getImgUrl());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public ImgDto findByDiaryId(String diaryId) {
        Img imgByBlogId = imgRepository.findImgByBlogId(diaryId);
        return imgMapper.toDto(imgByBlogId);
    }
}
