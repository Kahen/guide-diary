package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.ActiveCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kahen
 * @date 2021/2/11
 */
public interface ActiveCodeRepository extends JpaRepository<ActiveCode, String> {

}
