package practice.postpractice.domain.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.postpractice.domain.dto.response.SuccessResponseDto;
import practice.postpractice.domain.dto.response.SuccessResponseDtoWithId;
import practice.postpractice.domain.movie.dto.CreateMovieDto;
import practice.postpractice.domain.movie.dto.MovieQueryOption;
import practice.postpractice.domain.movie.dto.MovieResponseDto;
import practice.postpractice.domain.movie.dto.PageMovieResponseDto;
import practice.postpractice.domain.movie.service.LikeService;
import practice.postpractice.domain.movie.service.MovieService;
import practice.postpractice.global.utils.SecurityUtil;
/**
 * <br>package name   : practice.postpractice.domain.controller
 * <br>file name      : MovieController
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
@Tag(name = "Movie Management",description = "Movie API")
@Slf4j
public class MovieController {

    private final MovieService movieService;
    private final LikeService likeService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "영화 등록하기", description = "유저가 원하는 영화 등록 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<SuccessResponseDtoWithId> createMovie(@RequestPart(value = "movie") CreateMovieDto dto,
                                                                @RequestPart(value = "image") MultipartFile file) {
        Long id = movieService.createMovie(dto,file);
        SuccessResponseDtoWithId response = SuccessResponseDtoWithId.of(id,"영화 등록 완료!");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "모든 영화 조회", description = "필터링 없이 모든 영화 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<PageMovieResponseDto> findAllMovies(Pageable pageable) {
        Page<MovieResponseDto> movies = movieService.getAllMovies(pageable);
        PageMovieResponseDto response = PageMovieResponseDto.from(movies);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "필터링 영화 조회", description = "필터링해서 영화 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<PageMovieResponseDto> findMovie(@RequestParam MovieQueryOption movieQueryOption,Pageable pageable) {
        Page<MovieResponseDto> movies = movieService.findMovies(movieQueryOption,pageable);
        PageMovieResponseDto response = PageMovieResponseDto.from(movies);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "id로 영화 조회", description = "특정 영화 클릭시 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<MovieResponseDto> findMovie(@PathVariable Long movieId) {
        MovieResponseDto response = movieService.getMovie(movieId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/member/likes")
    @Operation(summary = "좋아요 누른 영화 조회",description = "로그인한 멤버가 좋아요 한 영화 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<PageMovieResponseDto> findMovieLikes(Pageable pageable) {
        String username = SecurityUtil.getCurrentUsername();
        Page<MovieResponseDto> movies = likeService.getMembersLikeMovies(username,pageable);
        PageMovieResponseDto response = PageMovieResponseDto.from(movies);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "영화 삭제",description = "특정 id를 가진 영화를 지우는 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<SuccessResponseDto> deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok(new SuccessResponseDto("영화 삭제 성공!"));
    }
}

