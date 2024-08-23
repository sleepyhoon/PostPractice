package practice.postpractice.domain.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.domain.Like;
import practice.postpractice.domain.movie.domain.Movie;

/**
 * <br>package name   : practice.postpractice.domain.movie.dao
 * <br>file name      : Likerepository
 * <br>date           : 2024-08-23
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
 * 2024-08-23        SeungHoon              init create
 * </pre>
 */
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByMemberAndMovie(Member member, Movie movie);
}
