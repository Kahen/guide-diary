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
package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.CommentLike;
import me.zhengjie.modules.blog.service.dto.CommentLikeDto;
import me.zhengjie.modules.blog.service.dto.CommentLikeQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-12-06
 **/
public interface CommentLikeService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map
            <String, Object> queryAll(CommentLikeQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <CommentLikeDto>
     */
    List
            <CommentLikeDto> queryAll(CommentLikeQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param likeId ID
     * @return CommentLikeDto
     */
    CommentLikeDto findById(Long likeId);

    /**
     * 创建
     *
     * @param resources /
     * @return CommentLikeDto
     */
    CommentLikeDto create(CommentLike resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(CommentLike resources);

    /**
     * 多选删除
     *
     * @param ids /
     */
    void deleteAll(Long[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List
                          <CommentLikeDto> all, HttpServletResponse response) throws IOException;
}