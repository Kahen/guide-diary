
package me.zhengjie.modules.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.repository.DiaryUserRepository;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.service.dto.DiaryUserDto;
import me.zhengjie.modules.blog.service.dto.DiaryUserQueryCriteria;
import me.zhengjie.modules.blog.service.mapstruct.DiaryUserMapper;
import me.zhengjie.modules.blog.utils.DateFormatUtils;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kahen
 *
 * @description 服务实现
 * @date 2020-12-09
 **/
@Service
@RequiredArgsConstructor
public class DiaryUserServiceImpl implements DiaryUserService {

    private final DiaryUserRepository diaryUserRepository;
    private final DiaryUserMapper diaryUserMapper;

    @Override
    public Map<String, Object> queryAll(DiaryUserQueryCriteria criteria, Pageable pageable) {
        Page<DiaryUser> page = diaryUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(diaryUserMapper::toDto));
    }

    @Override
    public List
            <DiaryUserDto> queryAll(DiaryUserQueryCriteria criteria) {
        return diaryUserMapper.toDto(diaryUserRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public DiaryUserDto findById(String uid) {
        DiaryUser diaryUser = diaryUserRepository.findById(uid).orElseGet(DiaryUser
                ::new);
        ValidationUtil.isNull(diaryUser.getUid(), "DiaryUser", "uid", uid);
        return diaryUserMapper.toDto(diaryUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DiaryUserDto create(DiaryUser resources) {
        resources.setUid(IdUtil.simpleUUID());
        return diaryUserMapper.toDto(diaryUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DiaryUser resources) {
        DiaryUser diaryUser = diaryUserRepository.findById(resources.getUid
                ()).orElseGet(DiaryUser::new);
        ValidationUtil.isNull(diaryUser.getUid(), "DiaryUser", "id", resources.getUid());
        diaryUser.copy(resources);
        diaryUserRepository.save(diaryUser);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String uid : ids) {
            diaryUserRepository.deleteById(uid);
        }
    }

    @Override
    public void download(List<DiaryUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DiaryUserDto diaryUser : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户名", diaryUser.getName());
            map.put("昵称", diaryUser.getNickname());
            map.put("用户介绍", diaryUser.getDescription());
            map.put("粉丝数", diaryUser.getFollowersCount());
            map.put("关注数", diaryUser.getFriendsCount());
            map.put("博客数", diaryUser.getStatusesCount());
            map.put("创建时间", diaryUser.getCreateTime());
            map.put("头像链接", diaryUser.getAvatarUrl());
//            map.put("密码哈希", diaryUser.getPassword());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public DiaryUser buildDiaryUser(JSONObject userObject) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new DiaryUser()
                .setUid(userObject.getLong("id").toString())
                .setName(userObject.getString("name"))
                .setNickname(userObject.getString("screen_name"))
                .setCreateTime(new Timestamp(DateFormatUtils.formatDate(userObject.getString("created_at"))))
                .setAvatarUrl("profile_image_url")
                .setDescription(userObject.getString("description"))
                .setFollowersCount(userObject.getLong("followers_count"))
                .setFriendsCount(userObject.getLong("friends_count"))
                .setPassword(passwordEncoder.encode("123456"))
                .setStatusesCount(userObject.getLong("statuses_count"));
    }

    @Override
    public void saveAll(List<DiaryUser> diaryUsers) {
        diaryUserRepository.saveAll(diaryUsers);
    }
}
