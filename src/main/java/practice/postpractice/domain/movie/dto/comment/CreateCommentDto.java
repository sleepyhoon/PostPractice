package practice.postpractice.domain.movie.dto.comment;

/**
 * <br>package name   : practice.postpractice.domain.movie.dto.comment
 * <br>file name      : CreateCommentDto
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
public record CreateCommentDto (
        Long movie_id,
        String username,
        String content
) {
    public static CreateCommentDto of(Long movie_id, String username, String content) {
        return new CreateCommentDto(movie_id, username, content);
    }
}
