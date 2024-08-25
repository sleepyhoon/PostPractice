package practice.postpractice.domain.dto.response;

/**
 * <br>package name   : practice.postpractice.domain
 * <br>file name      : SuccessResponseDto
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
public record SuccessResponseDto(
        String message
) {
    public static SuccessResponseDto from(String message) {
        return new SuccessResponseDto(message);
    }
}
