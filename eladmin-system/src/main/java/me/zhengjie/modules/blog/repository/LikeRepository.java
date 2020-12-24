
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 *
 * @date 2020-12-09
 **/
public interface LikeRepository extends JpaRepository<Like, String>, JpaSpecificationExecutor<Like> {
}
