package practice.postpractice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.CreateMovieDto;
import practice.postpractice.domain.movie.dto.MovieQueryOption;
import practice.postpractice.domain.movie.dto.MovieResponseDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.MovieManageException;

import java.io.File;
import java.io.IOException;
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

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Override
    public Long createMovie(CreateMovieDto dto, MultipartFile file) {
        String imgPath = saveFile(file, dto);
        Movie movie = Movie.create(dto,imgPath);
        try {
            movieRepository.save(movie);
            return movie.getId();
        } catch (DataIntegrityViolationException e) {
            throw new MovieManageException(ErrorCode.DUPLICATE_MOVIE);
        }
    }

    @Override
    public Page<MovieResponseDto> findMovies(MovieQueryOption queryOption,Pageable pageable) {
        Page<Movie> movies = movieRepository.searchMovies(queryOption,pageable);
        return movies.map(MovieResponseDto::from);
    }

    @Override
    public Page<MovieResponseDto> getAllMovies(Pageable pageable){
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        return moviePage.map(MovieResponseDto::from);
    }

    @Override
    public MovieResponseDto getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        return MovieResponseDto.from(movie);
    }

    public String saveFile(MultipartFile file, CreateMovieDto dto) {
        if (file == null || file.isEmpty()) {
            // 파일이 없거나 비어있는 경우 예외 던지기
            throw new MovieManageException(ErrorCode.INVALID_FILE);
        }

        String filename = "movie_" + dto.title() + "_" + file.getOriginalFilename();
        File destinationFile = new File(uploadDir + File.separator + filename);

        try {
            // 필요한 경우 디렉토리 생성
            destinationFile.getParentFile().mkdirs();

            // 파일 저장
            file.transferTo(destinationFile);

            // 저장된 파일 경로 반환
            return "/static/images/" + filename;

        } catch (IOException e) {
            // 파일 저장 중 오류가 발생하면 예외를 던짐
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
