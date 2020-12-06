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
import me.zhengjie.modules.blog.domain.Repost;
import me.zhengjie.modules.blog.service.RepostService;
import me.zhengjie.modules.blog.service.dto.RepostQueryCriteria;
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
@Api(tags = "blog_repost管理")
@RequestMapping("/api/repost")
public class RepostController {

    private final RepostService repostService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('repost:list')")
    public void download(HttpServletResponse response, RepostQueryCriteria criteria) throws IOException {
        repostService.download(repostService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog_repost")
    @ApiOperation("查询blog_repost")
    @PreAuthorize("@el.check('repost:list')")
    public ResponseEntity<Object> query(RepostQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(repostService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog_repost")
    @ApiOperation("新增blog_repost")
    @PreAuthorize("@el.check('repost:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Repost resources) {
        return new ResponseEntity<>(repostService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog_repost")
    @ApiOperation("修改blog_repost")
    @PreAuthorize("@el.check('repost:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Repost resources) {
        repostService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog_repost")
    @ApiOperation("删除blog_repost")
    @PreAuthorize("@el.check('repost:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        repostService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}