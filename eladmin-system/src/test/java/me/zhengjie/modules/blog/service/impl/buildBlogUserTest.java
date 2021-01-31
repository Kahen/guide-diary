package me.zhengjie.modules.blog.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.service.DiaryUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class buildBlogUserTest {

    @Autowired
    private DiaryUserService diaryUserService;

    @Test
    void testBuildBlogUser() {

        String s = HttpUtil.get("https://api.weibo.com/2/users/show.json?access_token=2.00CcTx1ClhslKB466ae0c66enFS86C&uid=2141967570");
        JSONObject userObject = JSON.parseObject(s);

        DiaryUser diaryUser = diaryUserService.buildDiaryUser(userObject);
        Assertions.assertNotNull(diaryUser);
    }
}
