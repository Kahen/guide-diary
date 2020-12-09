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

import com.alibaba.fastjson.JSONObject;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.service.dto.DiaryUserDto;
import me.zhengjie.modules.blog.service.dto.DiaryUserQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @description 服务接口
 * @date 2020-12-09
 **/
public interface DiaryUserService {

    /**
     * 查询数据分页
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map
     * <String,Object>
     */
    Map<String, Object> queryAll(DiaryUserQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     *
     * @param criteria 条件参数
     * @return List
     * <DiaryUserDto>
     */
    List<DiaryUserDto> queryAll(DiaryUserQueryCriteria criteria);

    /**
     * 根据ID查询
     *
     * @param uid ID
     * @return DiaryUserDto
     */
    DiaryUserDto findById(String uid);

    /**
     * 创建
     *
     * @param resources /
     * @return DiaryUserDto
     */
    DiaryUserDto create(DiaryUser resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(DiaryUser resources);

    /**
     * 多选删除
     *
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
    void download(List<DiaryUserDto> all, HttpServletResponse response) throws IOException;

    public DiaryUser buildDiaryUser(JSONObject userObject);

    void saveAll(List<DiaryUser> diaryUsers);
}
