package practice.postpractice.domain.movie.dto.movie;

import java.time.LocalDateTime;
import java.util.List;
import practice.postpractice.domain.movie.domain.Genre;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.domain.MovieGenre;

/**
 * <br>package name   : practice.postpractice.domain.movie.dto
 * <br>file name      : ResponseMovieDto
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
public record ProblemMovieResponseDto(
        String title,
        String author,
        List<String> genres,
        LocalDateTime createdAt
) {
    public static ProblemMovieResponseDto from(Movie movie) {
        List<String> genres = movie.getMovieGenreList().stream().map(MovieGenre::getGenre)
                .map(Genre::getName).toList();
        return new ProblemMovieResponseDto(movie.getTitle(),movie.getAuthor(), genres, movie.getCreatedAt());
    }
}
