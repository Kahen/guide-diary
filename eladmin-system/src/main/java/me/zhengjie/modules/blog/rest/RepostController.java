
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
@Api(tags = "blog_repost管理")
@RequestMapping("/api/repost")
public class RepostController {

    private final RepostService repostService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check('repost:list')")
    public void download(HttpServletResponse response, RepostQueryCriteria criteria) throws IOException {
        repostService.download(repostService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询blog_repost")
    @ApiOperation("查询blog_repost")
//    @PreAuthorize("@el.check('repost:list')")
    public ResponseEntity
            <Object> query(RepostQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(repostService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增blog_repost")
    @ApiOperation("新增blog_repost")
//    @PreAuthorize("@el.check('repost:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody Repost resources) {
        return new ResponseEntity<>(repostService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改blog_repost")
    @ApiOperation("修改blog_repost")
//    @PreAuthorize("@el.check('repost:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody Repost resources) {
        repostService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除blog_repost")
    @ApiOperation("删除blog_repost")
//    @PreAuthorize("@el.check('repost:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        repostService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
