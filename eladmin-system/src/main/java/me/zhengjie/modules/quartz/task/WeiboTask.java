
package me.zhengjie.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.blog.service.BuildBlogService;
import org.springframework.stereotype.Component;

/**
 * 测试用
 *
 * @author Kahen
 * @date 2019-01-08
 */
@Slf4j
@Component
public class WeiboTask {
    private final BuildBlogService buildBlogService;

    public WeiboTask(BuildBlogService buildBlogService) {
        this.buildBlogService = buildBlogService;
    }

    public void run() {
        log.info("run 执行成功");
        buildBlogService.build();
    }

}
