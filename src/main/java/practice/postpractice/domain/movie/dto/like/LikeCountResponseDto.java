package practice.postpractice.domain.movie.dto.like;

/**
 * <br>package name   : practice.postpractice.domain.movie.dto
 * <br>file name      : LikeCountResponseDto
 * <br>date           : 2024-08-26
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
 * 2024-08-26        SeungHoon              init create
 * </pre>
 */
public record LikeCountResponseDto(
        int count
) {
    public static LikeCountResponseDto of(int count) {
        return new LikeCountResponseDto(count);
    }
}
