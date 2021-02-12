package me.zhengjie.modules.blog.utils;

import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.extra.mail.MailUtil;
import org.junit.jupiter.api.Test;

/**
 * @author kahen
 * @date 2021/2/9
 */
public class HutoolTest {

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
}
