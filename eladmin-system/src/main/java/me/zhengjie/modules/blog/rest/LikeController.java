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
import me.zhengjie.modules.blog.domain.Like;
import me.zhengjie.modules.blog.service.LikeService;
import me.zhengjie.modules.blog.service.dto.LikeQueryCriteria;
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
 * @date 2020-12-05
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "blog-like管理")
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('like:list')")
    public void download(HttpServletResponse response, LikeQueryCriteria criteria) throws IOException {
        likeService.download(likeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog-like")
    @ApiOperation("查询blog-like")
    @PreAuthorize("@el.check('like:list')")
    public ResponseEntity<Object> query(LikeQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(likeService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog-like")
    @ApiOperation("新增blog-like")
    @PreAuthorize("@el.check('like:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Like resources) {
        return new ResponseEntity<>(likeService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog-like")
    @ApiOperation("修改blog-like")
    @PreAuthorize("@el.check('like:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Like resources) {
        likeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog-like")
    @ApiOperation("删除blog-like")
    @PreAuthorize("@el.check('like:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        likeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}