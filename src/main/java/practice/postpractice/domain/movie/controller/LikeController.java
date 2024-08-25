package practice.postpractice.domain.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.postpractice.domain.dto.response.LikeResponseDto;
import practice.postpractice.domain.dto.response.SuccessResponseDto;
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
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;


    // url 고민좀 해봅시다.
    @PostMapping("/movies/{movieId}")
    public ResponseEntity<LikeResponseDto> likeMovie(@PathVariable Long movieId) {
        String username = SecurityUtil.getCurrentUsername();
        Long id = likeService.createLike(movieId, username);
        LikeResponseDto response = LikeResponseDto.of(id,"좋아요를 눌렀습니다");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/movies/{likeId}")
    public ResponseEntity<SuccessResponseDto> unlikeMovie(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
        SuccessResponseDto response = SuccessResponseDto.from("좋아요를 취소했습니다");
        return ResponseEntity.ok(response);
    }
}
