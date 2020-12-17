
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Repost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-09
 **/
public interface RepostRepository extends JpaRepository<Repost, String>, JpaSpecificationExecutor<Repost> {
}
