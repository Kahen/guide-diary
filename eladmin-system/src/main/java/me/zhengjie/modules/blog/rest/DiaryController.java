/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.Diary;
import me.zhengjie.modules.blog.service.DiaryService;
import me.zhengjie.modules.blog.service.dto.DiaryQueryCriteria;
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
@Api(tags = "diary管理")
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('diary:list')")
    public void download(HttpServletResponse response, DiaryQueryCriteria criteria) throws IOException {
        diaryService.download(diaryService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询diary")
    @ApiOperation("查询diary")
    @PreAuthorize("@el.check('diary:list')")
    public ResponseEntity
            <Object> query(DiaryQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(diaryService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增diary")
    @ApiOperation("新增diary")
    @PreAuthorize("@el.check('diary:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Diary resources) {
        return new ResponseEntity<>(diaryService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改diary")
    @ApiOperation("修改diary")
    @PreAuthorize("@el.check('diary:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Diary resources) {
        diaryService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除diary")
    @ApiOperation("删除diary")
    @PreAuthorize("@el.check('diary:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody Long[] ids) {
        diaryService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
