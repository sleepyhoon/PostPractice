package practice.postpractice.domain.movie.dto.movie;

/**
 * <br>package name   : practice.postpractice.domain.movie.dto
 * <br>file name      : CreateMovieDto
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
public record CreateMovieDto(
        String title,
        String author
) {
}
