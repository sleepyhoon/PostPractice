package practice.postpractice.domain.movie.dto.like;

/**
 * <br>package name   : practice.postpractice.domain.dto.response
 * <br>file name      : LikeResponseDto
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
public record LikeResponseDto(
        Long id,
        String message
) {
    public static LikeResponseDto of(Long id, String message) {
        return new LikeResponseDto(id, message);
    }
}
