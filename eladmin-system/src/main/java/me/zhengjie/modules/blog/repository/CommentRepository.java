
package me.zhengjie.modules.blog.repository;

import me.zhengjie.modules.blog.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Kahen
 * @date 2020-12-09
 **/
public interface CommentRepository extends JpaRepository<Comment, String>, JpaSpecificationExecutor<Comment> {
    Page<Comment> findCommentsByBlogId(String blogId, Pageable pageable);

    Long countCommentByBlogId(String blogId);
}
