package practice.postpractice.domain.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.postpractice.domain.movie.dto.MovieQueryOption;
import practice.postpractice.domain.movie.dto.ResponseMovieDto;
import practice.postpractice.domain.movie.service.LikeService;
import practice.postpractice.domain.movie.service.MovieService;
import practice.postpractice.global.utils.SecurityUtil;

import java.util.List;

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
public class MovieController {

    private final MovieService movieService;
    private final LikeService likeService;

    @GetMapping
    @Operation(summary = "모든 영화 조회", description = "필터링 없이 모든 영화 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<ResponseMovieDto>> findAllMovies() {
        List<ResponseMovieDto> response = movieService.getAllMovies();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @Operation(summary = "필터링 영화 조회", description = "필터링해서 영화 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<ResponseMovieDto>> findMovie(@RequestParam MovieQueryOption movieQueryOption) {
        List<ResponseMovieDto> response = movieService.findMovies(movieQueryOption);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}")
    @Operation(summary = "id로 영화 조회", description = "특정 영화 클릭시 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<ResponseMovieDto> findMovie(@PathVariable Long movieId) {
        ResponseMovieDto response = movieService.getMovie(movieId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/member/likes")
    @Operation(summary = "좋아요 누른 영화 조회",description = "로그인한 멤버가 좋아요 한 영화 조회 API")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<ResponseMovieDto>> findMovieLikes() {
        String username = SecurityUtil.getCurrentUsername();
        List<ResponseMovieDto> response = likeService.getMembersLikeMovies(username);
        return ResponseEntity.ok(response);
    }
}

