package practice.postpractice.domain.movie.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.postpractice.domain.BaseEntity;
import practice.postpractice.domain.member.domain.Member;

import java.time.LocalDateTime;

/**
 * <br>package name   : practice.postpractice.domain.domain
 * <br>file name      : Like
 * <br>date           : 2024-08-21
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
 * 2024-08-21        SeungHoon              init create
 * </pre>
 */
@Entity
@Table(name = "Likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Builder(access = AccessLevel.PRIVATE)
    public Like(Member member, Movie movie) {
        this.member = member;
        this.movie = movie;
    }

    public static Like create(Member member, Movie movie) {
        return Like.builder().member(member).movie(movie).build();
    }
}
