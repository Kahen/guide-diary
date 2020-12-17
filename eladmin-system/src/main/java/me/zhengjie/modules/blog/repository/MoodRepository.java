
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Mood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-16
 **/
public interface MoodRepository extends JpaRepository<Mood, String>, JpaSpecificationExecutor<Mood> {
}
