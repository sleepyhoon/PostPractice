package practice.postpractice.domain.dto.response;

/**
 * <br>package name   : practice.postpractice.domain.dto.response
 * <br>file name      : SuccessResponseDtoWithId
 * <br>date           : 2024-08-28
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
 * 2024-08-28        SeungHoon              init create
 * </pre>
 */
public record SuccessResponseDtoWithId(
        Long id,
        String message
) {
}
