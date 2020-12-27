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

import me.zhengjie.modules.blog.domain.Motto;
import me.zhengjie.modules.blog.service.dto.MottoDto;
import me.zhengjie.modules.blog.service.dto.MottoQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-12-27
 **/
public interface MottoService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String, Object>
     */
    Map<String, Object> queryAll(MottoQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List<MottoDto>
     */
    List<MottoDto> queryAll(MottoQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return MottoDto
     */
    MottoDto findById(String id);

    /**
     * 创建
     * @param resources /
    * @return MottoDto
    */
    MottoDto create(Motto resources);

    /**
     * 编辑
    * @param resources /
    */
    void update(Motto resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(String[] ids);

    /**
     * 导出数据
     *
     * @param all      待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<MottoDto> all, HttpServletResponse response) throws IOException;
}