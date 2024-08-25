package practice.postpractice.domain.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.postpractice.domain.dto.response.LikeResponseDto;
import practice.postpractice.domain.dto.response.SuccessResponseDto;
import practice.postpractice.domain.member.domain.Member;
import practice.postpractice.domain.movie.service.LikeService;
import practice.postpractice.global.utils.SecurityUtil;

/**
 * <br>package name   : practice.postpractice.domain.movie.controller
 * <br>file name      : LikeController
 * <br>date           : 2024-08-25
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
 * 2024-08-25        SeungHoon              init create
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/movies/{movieId}/likes")
@Tag(name = "Like Management",description = "Like API")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    @Operation(summary = "좋아요 누르기", description = "해당 영화에 대한 좋아요")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<LikeResponseDto> likeMovie(@PathVariable Long movieId) {
        String username = SecurityUtil.getCurrentUsername();
        Long id = likeService.createLike(movieId, username);
        LikeResponseDto response = LikeResponseDto.of(id,"좋아요를 눌렀습니다");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(summary = "좋아요 취소하기", description = "해당 영화에 대한 좋아요 취소")
    @ApiResponse(responseCode = "200",description = "요청에 성공하였습니다",content = @Content(mediaType = "application/json"))
    public ResponseEntity<SuccessResponseDto> unlikeMovie(@PathVariable Long movieId) {
        String username = SecurityUtil.getCurrentUsername();
        likeService.deleteLike(movieId, username); // 수정된 메소드로 영화 ID와 사용자명을 사용
        SuccessResponseDto response = SuccessResponseDto.from("좋아요를 취소했습니다");
        return ResponseEntity.ok(response);
    }
}

