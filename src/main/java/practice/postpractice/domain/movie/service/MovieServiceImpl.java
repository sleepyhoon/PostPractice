package practice.postpractice.domain.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practice.postpractice.domain.movie.dao.MovieRepository;
import practice.postpractice.domain.movie.domain.Movie;
import practice.postpractice.domain.movie.dto.movie.CreateMovieDto;
import practice.postpractice.domain.movie.dto.movie.MovieQueryOption;
import practice.postpractice.domain.movie.dto.movie.MovieResponseDto;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.MovieManageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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

    private static final String UPLOAD_DIR = "uploads";

    @Override
    public Long createMovie(CreateMovieDto dto, MultipartFile file) {
        String imgPath;
        try {
            // 파일 저장
            imgPath = saveFile(file);
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

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                ()-> new MovieManageException(ErrorCode.NOT_EXIST_MOVIE));
        String imgPath = movie.getImgPath();
        // 1. 이미지 파일 삭제
        if (imgPath != null && !imgPath.isEmpty()) {
            deleteFile(imgPath);
        }
        // 2. 영화 데이터베이스에서 삭제
        movieRepository.delete(movie);
    }

    private String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename;
        Path filePath;

        try {
            // 1. 원본 파일명 가져오기
            if (originalFilename == null) {
                throw new IOException("파일 이름이 존재하지 않습니다.");
            }

            // 2. 고유한 파일명 생성 (UUID + 원본 파일 확장자)
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            uniqueFilename = UUID.randomUUID() + extension;

            // 3. 파일 저장 경로 생성
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 4. 파일 저장
            filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("파일 저장 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 저장 실패", e);
        } catch (Exception e) {
            log.error("예상치 못한 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 처리 중 예상치 못한 오류가 발생했습니다.", e);
        }

        // 5. 저장된 파일 경로 반환
        return filePath.toString();
    }

    private void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            log.info("파일 삭제 성공: {}", filePath);
        } catch (IOException e) {
            log.error("파일 삭제 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 삭제 실패", e);
        }
    }
}
