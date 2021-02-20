
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.config.FileProperties;
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
import org.apache.poi.util.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
@EnableAsync
@Slf4j
public class ImgServiceImpl implements ImgService {

    private final ImgRepository imgRepository;
    private final ImgMapper imgMapper;
    private final FileProperties fileProperties;

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

    @Override
    @Async
    public void fetchImages(String blogId, JSONArray picUrls) {
        JSONArray jsonArray = new JSONArray();
        for (Object picUrl : picUrls) {
            String url = (String) picUrl;
            File file = HttpUtil.downloadFileFromUrl(url, "~/");
            FileInputStream input = null;
            try {
                input = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            MultipartFile multipartFile = null;
            try {
                assert input != null;
                multipartFile = new MockMultipartFile(file.getName(), file.getName(), "text/plain", IOUtils.toByteArray(input));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert multipartFile != null;
            String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
            String type = FileUtil.getFileType(suffix);
            File upload = FileUtil.upload(multipartFile, fileProperties.getPath().getPath() + type + File.separator);

            assert upload != null;
            jsonArray.add(upload.getName());
            boolean delete = file.delete();
            log.info(String.valueOf(delete));
        }
        imgRepository.save(new
                Img().setImgId(IdUtil.simpleUUID()).setImgUrl(jsonArray.toJSONString()).setBlogId(blogId));
    }
}
