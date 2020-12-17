
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @website https://el-admin.vip
 * @date 2020-12-16
 **/
public interface DiaryRepository extends JpaRepository<Diary, Long>, JpaSpecificationExecutor<Diary> {
}