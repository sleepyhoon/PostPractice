package practice.postpractice.domain.movie.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import practice.postpractice.domain.movie.dto.CreateMovieDto;
import practice.postpractice.domain.movie.dto.MovieQueryOption;
import practice.postpractice.domain.movie.dto.MovieResponseDto;

import java.io.IOException;
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
    Long createMovie(CreateMovieDto dto, MultipartFile file);
    Page<MovieResponseDto> findMovies(MovieQueryOption queryOption,Pageable pageable);
    Page<MovieResponseDto> getAllMovies(Pageable pageable);
    MovieResponseDto getMovie(Long movieId);
    void deleteMovie(Long movieId);
}
