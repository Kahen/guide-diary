
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
     * @param userId /
     * @return /
     */
    Page<Diary> findDiaryByUserIdOrderByDayTimestampDesc(String userId, Pageable pageable);

}
