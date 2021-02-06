
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @date 2020-12-09
 **/
public interface CollectRepository extends JpaRepository<Collect, String>, JpaSpecificationExecutor<Collect> {
    Collect findCollectByBlogIdAndUserId(String blogId, String userId);

    Long countCollectsByBlogId(String blogId);
}
