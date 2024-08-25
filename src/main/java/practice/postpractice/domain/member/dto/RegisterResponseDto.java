package practice.postpractice.domain.member.dto;

/**
 * <br>package name   : practice.postpractice.domain.member.dto
 * <br>file name      : RegisterResponseDto
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
public record RegisterResponseDto(
        long id,
        String message
) {
    public static RegisterResponseDto of(long id, String message) {
        return new RegisterResponseDto(id, message);
    }
}
