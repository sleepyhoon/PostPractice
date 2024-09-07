package practice.postpractice.domain.movie.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.postpractice.domain.BaseEntity;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.dto.comment.CreateCommentDto;

/**
 * <br>package name   : practice.postpractice.domain.movie.domain
 * <br>file name      : Comment
 * <br>date           : 2024-08-22
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
 * 2024-08-22        SeungHoon              init create
 * </pre>
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member writer; // 작성자 정보

    @Column(nullable = false)
    private String content;

    @Builder(access = AccessLevel.PRIVATE)
    public Comment(Movie movie, Member writer, String content) {
        this.movie = movie;
        this.writer = writer;
        this.content = content;
    }

    public static Comment create(Movie movie, Member writer, String content) {
        return Comment.builder().movie(movie).writer(writer).content(content).build();
    }
}
