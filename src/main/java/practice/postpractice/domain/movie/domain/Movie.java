package practice.postpractice.domain.movie.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.postpractice.domain.movie.dto.CreateMovieDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "moive_id")
    private long id;

    private String title;
    private String imgPath;

    @Builder(access = AccessLevel.PRIVATE)
    public Movie(String title, String imgPath) {
        this.title = title;
        this.imgPath = imgPath;
    }

    public static Movie create(CreateMovieDto dto) {
        String imgPath = "/static/images";
        return Movie.builder()
                .title(dto.title())
                .imgPath(imgPath)
                .build();
    }
}
