
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.Mood;
import me.zhengjie.modules.blog.service.MoodService;
import me.zhengjie.modules.blog.service.dto.MoodQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-16
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "guide_mood管理")
@RequestMapping("/api/mood")
public class MoodController {

    private final MoodService moodService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('mood:list')")
    public void download(HttpServletResponse response, MoodQueryCriteria criteria) throws IOException {
        moodService.download(moodService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询guide_mood")
    @ApiOperation("查询guide_mood")
    @PreAuthorize("@el.check('mood:list')")
    public ResponseEntity
            <Object> query(MoodQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(moodService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增guide_mood")
    @ApiOperation("新增guide_mood")
    @PreAuthorize("@el.check('mood:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Mood resources) {
        return new ResponseEntity<>(moodService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改guide_mood")
    @ApiOperation("修改guide_mood")
    @PreAuthorize("@el.check('mood:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Mood resources) {
        moodService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除guide_mood")
    @ApiOperation("删除guide_mood")
    @PreAuthorize("@el.check('mood:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        moodService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
