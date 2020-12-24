
package me.zhengjie.modules.blog.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.blog.domain.CommentLike;
import me.zhengjie.modules.blog.service.CommentLikeService;
import me.zhengjie.modules.blog.service.dto.CommentLikeQueryCriteria;
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
@Api(tags = "comment_like管理")
@RequestMapping("/api/commentLike")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('commentLike:list')")
    public void download(HttpServletResponse response, CommentLikeQueryCriteria criteria) throws IOException {
        commentLikeService.download(commentLikeService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询comment_like")
    @ApiOperation("查询comment_like")
    @PreAuthorize("@el.check('commentLike:list')")
    public ResponseEntity
            <Object> query(CommentLikeQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(commentLikeService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增comment_like")
    @ApiOperation("新增comment_like")
    @PreAuthorize("@el.check('commentLike:add')")
    public ResponseEntity
            <Object> create(@Validated @RequestBody CommentLike resources) {
        return new ResponseEntity<>(commentLikeService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改comment_like")
    @ApiOperation("修改comment_like")
    @PreAuthorize("@el.check('commentLike:edit')")
    public ResponseEntity
            <Object> update(@Validated @RequestBody CommentLike resources) {
        commentLikeService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除comment_like")
    @ApiOperation("删除comment_like")
    @PreAuthorize("@el.check('commentLike:del')")
    @DeleteMapping
    public ResponseEntity
            <Object> delete(@RequestBody String[] ids) {
        commentLikeService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
