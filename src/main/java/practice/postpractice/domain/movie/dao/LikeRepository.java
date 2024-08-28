package practice.postpractice.domain.movie.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.domain.Like;
import practice.postpractice.domain.movie.domain.Movie;

import java.util.List;
import java.util.Optional;

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
    boolean existsByMemberId(Long memberId);
    Page<Like> findByMemberId(Long memberId, Pageable pageable);
    Optional<Like> findByMovieIdAndMemberId(long movieId, long memberId);

    // 특정 movieId를 가진 좋아요 개수를 반환하는 메서드
    @Query("SELECT COUNT(l) FROM Like l WHERE l.movie.id = :movieId")
    int countByMovieId(@Param("movieId") Long movieId);
}
