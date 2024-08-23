package practice.postpractice.domain.movie.dto;

import practice.postpractice.domain.movie.domain.Movie;

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
public record ResponseMovieDto(
        String title,
        String imgPath
) {
    public static ResponseMovieDto from(Movie movie) {
        return new ResponseMovieDto(movie.getTitle(), movie.getImgPath());
    }
}
