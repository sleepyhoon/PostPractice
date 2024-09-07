package practice.postpractice.domain.movie.service;

import practice.postpractice.domain.movie.domain.Comment;
import practice.postpractice.domain.movie.dto.comment.CreateCommentDto;

/**
 * <br>package name   : practice.postpractice.domain.movie.service
 * <br>file name      : CommentService
 * <br>date           : 2024-09-04
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
 * 2024-09-04        SeungHoon              init create
 * </pre>
 */
public interface CommentService {
    Long createComment(CreateCommentDto dto);
}
