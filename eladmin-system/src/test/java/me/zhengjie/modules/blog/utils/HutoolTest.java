package me.zhengjie.modules.blog.utils;

import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.mail.MailUtil;
import me.zhengjie.config.FileProperties;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

/**
 * @author kahen
 * @date 2021/2/9
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HutoolTest {
    @Autowired
    private FileProperties fileProperties;

    @Test
    void testMail() {
        MailUtil.send("kahenlee@163.com", "格子日志", "【格子日志】验证码：123456，用于格子日志账号注册与密码重置验证。泄露有风险，请勿转发。", true);
    }

    @Test
    void test() {
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String generate = randomGenerator.generate();
    }

    @Test
    void testFetchImage() {
        new MockMultipartFile("", new byte[]{});
        URL url = URLUtil.url("http://wx2.sinaimg.cn/thumbnail/005SDHXpgy1gnueg8pg0mj32bc3347wj.jpg");
        String file1 = url.getFile();
        String path = url.getPath();
//        try {
//            try {
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertNotNull(file);
    }
}
