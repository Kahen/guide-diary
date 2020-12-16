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
import me.zhengjie.modules.blog.domain.Tips;
import me.zhengjie.modules.blog.service.TipsService;
import me.zhengjie.modules.blog.service.dto.TipsQueryCriteria;
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
@Api(tags = "guide_tips管理")
@RequestMapping("/api/tips")
public class TipsController {

    private final TipsService tipsService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tips:list')")
    public void download(HttpServletResponse response, TipsQueryCriteria criteria) throws IOException {
        tipsService.download(tipsService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询guide_tips")
    @ApiOperation("查询guide_tips")
    @PreAuthorize("@el.check('tips:list')")
    public ResponseEntity
            <Object> query(TipsQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(tipsService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增guide_tips")
    @ApiOperation("新增guide_tips")
    @PreAuthorize("@el.check('tips:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Tips resources) {
        return new ResponseEntity<>(tipsService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改guide_tips")
    @ApiOperation("修改guide_tips")
    @PreAuthorize("@el.check('tips:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Tips resources) {
        tipsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除guide_tips")
    @ApiOperation("删除guide_tips")
    @PreAuthorize("@el.check('tips:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody Long[] ids) {
        tipsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
