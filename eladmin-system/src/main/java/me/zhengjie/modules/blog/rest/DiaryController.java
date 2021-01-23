
package me.zhengjie.modules.blog.rest;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.Diary;
import me.zhengjie.modules.blog.domain.Tips;
import me.zhengjie.modules.blog.service.DiaryService;
import me.zhengjie.modules.blog.service.ImgService;
import me.zhengjie.modules.blog.service.TipsService;
import me.zhengjie.modules.blog.service.dto.DiaryDto;
import me.zhengjie.modules.blog.service.dto.DiaryQueryCriteria;
import me.zhengjie.modules.blog.service.dto.ImgDto;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Kahen
 * @date 2021-01-11
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "diary管理")
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;
    private final TipsService tipsService;
    private final ImgService imgService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check('diary:list')")
    public void download(HttpServletResponse response, DiaryQueryCriteria criteria) throws IOException {
        diaryService.download(diaryService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询diary")
    @ApiOperation("查询diary")
//    @PreAuthorize("@el.check('diary:list')")
    public ResponseEntity
            <Object> query(DiaryQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(diaryService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @GetMapping("timeline")
    @Log("查询时间线")
    @ApiOperation("查询时间线")
    public ResponseEntity<Object> findAllByUser(@PageableDefault Pageable pageable, @RequestHeader HttpHeaders httpHeaders) {
//        if (!httpHeaders.containsKey("Authorization")) {
//            return new ResponseEntity<>("not user", HttpStatus.OK);
//        }
//        UserDto userInfo = onlineUserService.findOnlineUserInfo(httpHeaders.getFirst("Authorization"));
        JwtUserDto currentUser = (JwtUserDto) SecurityUtils.getCurrentUser();
        List<DiaryDto> diaryByUser = diaryService.findDiaryByUser(currentUser.getUser().getUid(), pageable);
        return new ResponseEntity<>(diaryByUser, HttpStatus.OK);
    }

    @PostMapping
    @Log("新增diary")
    @ApiOperation("新增diary")
//    @PreAuthorize("@el.check('diary:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Diary resources) {
        return new ResponseEntity<>(diaryService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改diary")
    @ApiOperation("修改diary")
//    @PreAuthorize("@el.check('diary:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Diary resources) {
        diaryService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除diary")
    @ApiOperation("删除diary")
//    @PreAuthorize("@el.check('diary:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        diaryService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findOne(@PathVariable(name = "id") String id, @RequestHeader HttpHeaders httpHeaders) {
//        if (!httpHeaders.containsKey("Authorization")) {
//            return new ResponseEntity<>("not user", HttpStatus.OK);
//        }
//        System.out.println(SecurityUtils.getCurrentUsername());
//        UserDto userInfo = onlineUserService.findOnlineUserInfo(httpHeaders.getFirst("Authorization"));
        JwtUserDto currentUser = (JwtUserDto) SecurityUtils.getCurrentUser();
        DiaryDto diaryDto = diaryService.findDiaryByUserIdAndDayTimestamp(currentUser.getUser().getUid(), id);
        if (diaryDto == null) {
            List<Tips> tips = tipsService.buildRamDomTips("daily");
            DiaryDto diaryDto1 = new DiaryDto();
            diaryDto1.setGuide1(tips.get(0).getContent());
            diaryDto1.setGuide2(tips.get(1).getContent());
            diaryDto1.setGuide3(tips.get(2).getContent());
            diaryDto1.setGuide4(tips.get(3).getContent());
            diaryDto1.setGuide5(tips.get(4).getContent());
            diaryDto1.setGuide6(tips.get(5).getContent());
            diaryDto1.setUserId(currentUser.getUser().getUid());
            return new ResponseEntity<>(diaryDto1, HttpStatus.OK);
        }
        ImgDto imgDto = imgService.findByDiaryId(diaryDto.getId());

        JSONArray array = JSONArray.parseArray(imgDto.getImgUrl());
        diaryDto.setImgUrls(array.toJavaList(String.class));
        return new ResponseEntity<>(diaryDto, HttpStatus.OK);
//        return new ResponseEntity<>( HttpStatus.OK);
    }
}
