package practice.postpractice.domain.movie.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practice.postpractice.domain.movie.dao.JpaMovieRepository;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.movie.CreateMovieDto;
import practice.postpractice.domain.movie.dto.movie.MovieQueryOption;
import practice.postpractice.domain.movie.dto.movie.MovieResponseDto;
import practice.postpractice.domain.movie.dto.movie.ProblemMovieResponseDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.MovieManageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import practice.postpractice.global.utils.FileUtils;

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
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final JpaMovieRepository jpaMovieRepository;

    @Override
    public Long createMovie(CreateMovieDto dto, MultipartFile file) {
        String imgPath;
        try {
            // 파일 저장
            imgPath = FileUtils.saveFile(file);
            // 영화 엔티티 생성 및 저장
            Movie movie = Movie.create(dto, imgPath);
            movieRepository.save(movie);
            // 성공 시 영화 ID 반환
            return movie.getId();

        } catch (DataIntegrityViolationException e) {
            // 데이터베이스 중복 저장 시 예외 처리
            throw new MovieManageException(ErrorCode.DUPLICATE_MOVIE);

        } catch (Exception e) {
            // 그 외 예외 처리
            throw new MovieManageException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Page<MovieResponseDto> findMovies(MovieQueryOption queryOption, Pageable pageable) {
        Page<Movie> movies = movieRepository.searchMovies(queryOption, pageable);
        return movies.map(MovieResponseDto::from);
    }

    @Override
    public Page<MovieResponseDto> getAllMovies(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.map(MovieResponseDto::from);
    }

    @Override
    public MovieResponseDto getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        return MovieResponseDto.from(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        String imgPath = movie.getImgPath();
        // 1. 이미지 파일 삭제
        if (imgPath != null && !imgPath.isEmpty()) {
            FileUtils.deleteFile(imgPath);
        }
        // 2. 영화 데이터베이스에서 삭제
        movieRepository.delete(movie);
    }

    @Override
    public List<ProblemMovieResponseDto> nProblemGetMovies() {
        return jpaMovieRepository.findAll()
                .stream()
                .map(ProblemMovieResponseDto::from)
                .toList();
    }

    @Override
    public List<ProblemMovieResponseDto> fetchJoinGetMovies() {
        return jpaMovieRepository.fetchAllMovies()
                .stream()
                .map(ProblemMovieResponseDto::from)
                .toList();
    }


}
