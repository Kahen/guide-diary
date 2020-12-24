
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.Motto;
import me.zhengjie.modules.blog.service.MottoService;
import me.zhengjie.modules.blog.service.dto.MottoQueryCriteria;
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
 * @date 2020-12-16
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "guide_motto管理")
@RequestMapping("/api/motto")
public class MottoController {

    private final MottoService mottoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('motto:list')")
    public void download(HttpServletResponse response, MottoQueryCriteria criteria) throws IOException {
        mottoService.download(mottoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询guide_motto")
    @ApiOperation("查询guide_motto")
    @PreAuthorize("@el.check('motto:list')")
    public ResponseEntity
            <Object> query(MottoQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(mottoService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增guide_motto")
    @ApiOperation("新增guide_motto")
    @PreAuthorize("@el.check('motto:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Motto resources) {
        return new ResponseEntity<>(mottoService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改guide_motto")
    @ApiOperation("修改guide_motto")
    @PreAuthorize("@el.check('motto:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Motto resources) {
        mottoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除guide_motto")
    @ApiOperation("删除guide_motto")
    @PreAuthorize("@el.check('motto:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody Long[] ids) {
        mottoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
