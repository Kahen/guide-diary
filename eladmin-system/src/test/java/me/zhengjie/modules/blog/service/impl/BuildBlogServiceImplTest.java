package me.zhengjie.modules.blog.service.impl;

import me.zhengjie.modules.blog.service.BuildBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lijiaxing
 * @date 2020/12/15
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BuildBlogServiceImplTest {

    @Autowired
    private BuildBlogService buildBlogService;

    @Test
    public void testBuild() {
        buildBlogService.build();
    }
}
