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
import me.zhengjie.modules.blog.domain.Tag;
import me.zhengjie.modules.blog.service.TagService;
import me.zhengjie.modules.blog.service.dto.TagQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-27
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "guide_tag管理")
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check('tag:list')")
    public void download(HttpServletResponse response, TagQueryCriteria criteria) throws IOException {
        tagService.download(tagService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询guide_tag")
    @ApiOperation("查询guide_tag")
//    @PreAuthorize("@el.check('tag:list')")
    public ResponseEntity<Object> query(TagQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(tagService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增guide_tag")
    @ApiOperation("新增guide_tag")
//    @PreAuthorize("@el.check('tag:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Tag resources) {
        return new ResponseEntity<>(tagService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改guide_tag")
    @ApiOperation("修改guide_tag")
//    @PreAuthorize("@el.check('tag:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Tag resources) {
        tagService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除guide_tag")
    @ApiOperation("删除guide_tag")
//    @PreAuthorize("@el.check('tag:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        tagService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
