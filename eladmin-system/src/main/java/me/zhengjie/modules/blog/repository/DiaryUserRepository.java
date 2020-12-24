
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.DiaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @date 2020-12-09
 **/
public interface DiaryUserRepository extends JpaRepository<DiaryUser, String>, JpaSpecificationExecutor<DiaryUser> {
    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return /
     */
    DiaryUser findDiaryUserByName(String name);
}
