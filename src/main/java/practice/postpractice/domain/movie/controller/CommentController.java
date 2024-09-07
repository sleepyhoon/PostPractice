package practice.postpractice.domain.movie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practice.postpractice.domain.dto.request.RequestCreateCommentDto;
import practice.postpractice.domain.movie.domain.Comment;
import practice.postpractice.domain.movie.dto.comment.CreateCommentDto;
import practice.postpractice.domain.movie.service.CommentService;
import practice.postpractice.global.utils.SecurityUtil;

/**
 * <br>package name   : practice.postpractice.domain.movie.controller
 * <br>file name      : CommentController
 * <br>date           : 2024-09-07
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
 * 2024-09-07        SeungHoon              init create
 * </pre>
 */
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/movies/{movie_id}/comment")
    public ResponseEntity<Long> addComment(@PathVariable("movie_id") Long movieId, @RequestBody RequestCreateCommentDto dto) {
        String username = SecurityUtil.getCurrentUsername();
        CreateCommentDto createCommentDto = CreateCommentDto.of(movieId,username,dto.content());
        Long id = commentService.createComment(createCommentDto);
        return ResponseEntity.ok(id);
    }
}
