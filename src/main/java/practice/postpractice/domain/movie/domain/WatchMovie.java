package practice.postpractice.domain.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import practice.postpractice.domain.member.domain.Member;

import java.time.LocalDateTime;

/**
 * <br>package name   : practice.postpractice.domain.movie.domain
 * <br>file name      : WatchMovie
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
@Entity
@Getter
public class WatchMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDateTime watchedAt;
}
