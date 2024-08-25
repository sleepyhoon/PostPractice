package practice.postpractice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.CreateMovieDto;
import practice.postpractice.domain.movie.dto.MovieQueryOption;
import practice.postpractice.domain.movie.dto.MovieResponseDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.MovieManageException;

import java.util.List;

/**
 * <br>package name   : practice.postpractice.domain.service
 * <br>file name      : MovieService
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
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Long createMovie(CreateMovieDto dto) {
        Movie movie = Movie.create(dto);
        try {
            movieRepository.save(movie);
            return movie.getId();
        } catch (DataIntegrityViolationException e) {
            throw new MovieManageException(ErrorCode.DUPLICATE_MOVIE);
        }
    }

    @Override
    public List<MovieResponseDto> findMovies(MovieQueryOption queryOption) {
        List<Movie> movies = movieRepository.searchMovies(queryOption);
        return movies.stream()
                .map(MovieResponseDto::from)
                .toList();
    }

    @Override
    public List<MovieResponseDto> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(MovieResponseDto::from)
                .toList();
    }

    public MovieResponseDto getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        return MovieResponseDto.from(movie);
    }
}
