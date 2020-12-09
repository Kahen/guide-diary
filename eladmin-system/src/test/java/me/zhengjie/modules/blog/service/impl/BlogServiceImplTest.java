package me.zhengjie.modules.blog.service.impl;

import me.zhengjie.modules.blog.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Kahen
 * @date : 2020/12/6 23:55
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BlogServiceImplTest {

    @Autowired
    private BlogService blogService;

    @Test
    public void testBuildBlog() {
//        blogService.buildBlog();
    }
}
