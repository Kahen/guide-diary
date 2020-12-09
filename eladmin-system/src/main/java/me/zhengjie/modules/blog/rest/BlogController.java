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
import me.zhengjie.modules.blog.domain.Blog;
import me.zhengjie.modules.blog.service.BlogService;
import me.zhengjie.modules.blog.service.dto.BlogQueryCriteria;
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
 * @date 2020-12-09
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "blog管理")
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('blog:list')")
    public void download(HttpServletResponse response, BlogQueryCriteria criteria) throws IOException {
        blogService.download(blogService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog")
    @ApiOperation("查询blog")
    @PreAuthorize("@el.check('blog:list')")
    public ResponseEntity
            <Object> query(BlogQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(blogService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog")
    @ApiOperation("新增blog")
    @PreAuthorize("@el.check('blog:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Blog resources) {
        return new ResponseEntity<>(blogService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog")
    @ApiOperation("修改blog")
    @PreAuthorize("@el.check('blog:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Blog resources) {
        blogService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog")
    @ApiOperation("删除blog")
    @PreAuthorize("@el.check('blog:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        blogService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
