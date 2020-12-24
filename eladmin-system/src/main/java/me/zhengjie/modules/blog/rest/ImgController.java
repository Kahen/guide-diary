
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.Img;
import me.zhengjie.modules.blog.service.ImgService;
import me.zhengjie.modules.blog.service.dto.ImgQueryCriteria;
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
@Api(tags = "blog_img管理")
@RequestMapping("/api/img")
public class ImgController {

    private final ImgService imgService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('img:list')")
    public void download(HttpServletResponse response, ImgQueryCriteria criteria) throws IOException {
        imgService.download(imgService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog_img")
    @ApiOperation("查询blog_img")
    @PreAuthorize("@el.check('img:list')")
    public ResponseEntity
            <Object> query(ImgQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(imgService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog_img")
    @ApiOperation("新增blog_img")
    @PreAuthorize("@el.check('img:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Img resources) {
        return new ResponseEntity<>(imgService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog_img")
    @ApiOperation("修改blog_img")
    @PreAuthorize("@el.check('img:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Img resources) {
        imgService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog_img")
    @ApiOperation("删除blog_img")
    @PreAuthorize("@el.check('img:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        imgService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
