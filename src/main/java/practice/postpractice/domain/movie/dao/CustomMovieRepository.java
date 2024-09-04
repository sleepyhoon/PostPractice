package practice.postpractice.domain.movie.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.movie.MovieQueryOption;

/**
 * <br>package name   : practice.postpractice.domain.movie.dao
 * <br>file name      : CustomMovieRepository
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
public interface CustomMovieRepository {
    Page<Movie> searchMovies(MovieQueryOption queryOption, Pageable pageable);
}
