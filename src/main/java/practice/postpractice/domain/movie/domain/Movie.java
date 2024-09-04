package practice.postpractice.domain.movie.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.postpractice.domain.movie.dto.movie.CreateMovieDto;

import java.time.LocalDateTime;

/**
 * <br>package name   : practice.postpractice.domain
 * <br>file name      : Movie
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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private long id;

    private String title;
    private String author;
    private String imgPath;
    private LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    public Movie(String title,String author,String imgPath) {
        this.title = title;
        this.imgPath = imgPath;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public static Movie create(CreateMovieDto dto,String imgPath) {
        return Movie.builder()
                .title(dto.title())
                .author(dto.author())
                .imgPath(imgPath)
                .build();
    }
}
