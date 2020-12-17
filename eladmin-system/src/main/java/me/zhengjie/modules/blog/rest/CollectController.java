
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.Collect;
import me.zhengjie.modules.blog.service.CollectService;
import me.zhengjie.modules.blog.service.dto.CollectQueryCriteria;
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
@Api(tags = "blog_collect管理")
@RequestMapping("/api/collect")
public class CollectController {

    private final CollectService collectService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('collect:list')")
    public void download(HttpServletResponse response, CollectQueryCriteria criteria) throws IOException {
        collectService.download(collectService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog_collect")
    @ApiOperation("查询blog_collect")
    @PreAuthorize("@el.check('collect:list')")
    public ResponseEntity
            <Object> query(CollectQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(collectService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog_collect")
    @ApiOperation("新增blog_collect")
    @PreAuthorize("@el.check('collect:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Collect resources) {
        return new ResponseEntity<>(collectService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog_collect")
    @ApiOperation("修改blog_collect")
    @PreAuthorize("@el.check('collect:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Collect resources) {
        collectService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog_collect")
    @ApiOperation("删除blog_collect")
    @PreAuthorize("@el.check('collect:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        collectService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
