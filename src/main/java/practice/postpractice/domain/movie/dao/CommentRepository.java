package practice.postpractice.domain.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.postpractice.domain.movie.domain.Comment;

/**
 * <br>package name   : practice.postpractice.domain.movie.dao
 * <br>file name      : CommentRepository
 * <br>date           : 2024-09-04
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 * <pre>
 * modified log :
 * =======================================================
 * DATE           AUTHOR               NOTE
 * -------------------------------------------------------
 * 2024-09-04        SeungHoon              init create
 * </pre>
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
