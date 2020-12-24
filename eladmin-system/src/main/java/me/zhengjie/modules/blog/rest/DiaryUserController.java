
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.service.dto.DiaryUserQueryCriteria;
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
 *
 * @date 2020-12-09
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "diary_user管理")
@RequestMapping("/api/diaryUser")
public class DiaryUserController {

    private final DiaryUserService diaryUserService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('diaryUser:list')")
    public void download(HttpServletResponse response, DiaryUserQueryCriteria criteria) throws IOException {
        diaryUserService.download(diaryUserService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询diary_user")
    @ApiOperation("查询diary_user")
    @PreAuthorize("@el.check('diaryUser:list')")
    public ResponseEntity
            <Object> query(DiaryUserQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(diaryUserService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增diary_user")
    @ApiOperation("新增diary_user")
    @PreAuthorize("@el.check('diaryUser:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody DiaryUser resources) {
        return new ResponseEntity<>(diaryUserService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改diary_user")
    @ApiOperation("修改diary_user")
    @PreAuthorize("@el.check('diaryUser:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody DiaryUser resources) {
        diaryUserService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除diary_user")
    @ApiOperation("删除diary_user")
    @PreAuthorize("@el.check('diaryUser:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        diaryUserService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
