
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @date 2021-01-11
 **/
public interface DiaryRepository extends JpaRepository<Diary, String>, JpaSpecificationExecutor<Diary> {
}
