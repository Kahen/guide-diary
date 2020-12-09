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
import me.zhengjie.modules.blog.domain.Comment;
import me.zhengjie.modules.blog.service.CommentService;
import me.zhengjie.modules.blog.service.dto.CommentQueryCriteria;
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
@Api(tags = "blog_comment管理")
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('comment:list')")
    public void download(HttpServletResponse response, CommentQueryCriteria criteria) throws IOException {
        commentService.download(commentService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog_comment")
    @ApiOperation("查询blog_comment")
    @PreAuthorize("@el.check('comment:list')")
    public ResponseEntity
            <Object> query(CommentQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(commentService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog_comment")
    @ApiOperation("新增blog_comment")
    @PreAuthorize("@el.check('comment:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Comment resources) {
        return new ResponseEntity<>(commentService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog_comment")
    @ApiOperation("修改blog_comment")
    @PreAuthorize("@el.check('comment:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Comment resources) {
        commentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog_comment")
    @ApiOperation("删除blog_comment")
    @PreAuthorize("@el.check('comment:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        commentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
