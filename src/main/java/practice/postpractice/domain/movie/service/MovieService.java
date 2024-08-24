package practice.postpractice.domain.movie.service;

import practice.postpractice.domain.movie.dto.CreateMovieDto;
import practice.postpractice.domain.movie.dto.MovieQueryOption;
import practice.postpractice.domain.movie.dto.ResponseMovieDto;

import java.util.List;

/**
 * <br>package name   : practice.postpractice.domain.movie.service
 * <br>file name      : MovieService
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
public interface MovieService {
    Long createMovie(CreateMovieDto dto);
    List<ResponseMovieDto> findMovies(MovieQueryOption queryOption);
    List<ResponseMovieDto> getAllMovies();
    ResponseMovieDto getMovie(Long movieId);
}
