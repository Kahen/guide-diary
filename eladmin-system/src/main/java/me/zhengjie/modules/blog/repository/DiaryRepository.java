
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Diary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Kahen
 * @date 2021-01-11
 **/
public interface DiaryRepository extends JpaRepository<Diary, String>, JpaSpecificationExecutor<Diary> {
    /**
     * 查找日记
     *
     * @param userId  /
     * @param dayTime /
     * @return /
     */
    Diary findDiaryByUserIdAndDayTimestamp(String userId, String dayTime);

    /**
     * 查时间线
     *
     * @param userId   /
     * @param pageable /
     * @return /
     */
    List<Diary> findDiaryByUserIdOrderByCreatedDateDesc(String userId, Pageable pageable);
}
