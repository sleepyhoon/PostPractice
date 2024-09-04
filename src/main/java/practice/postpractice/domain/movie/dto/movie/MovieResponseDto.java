package practice.postpractice.domain.movie.dto.movie;

import practice.postpractice.domain.movie.domain.Movie;

import java.time.LocalDateTime;

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
public record MovieResponseDto (
        String title,
        String author,
        LocalDateTime createdAt
) {
    public static MovieResponseDto from(Movie movie) {
        return new MovieResponseDto(movie.getTitle(),movie.getAuthor(),movie.getCreatedAt());
    }
}
