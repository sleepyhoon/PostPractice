package practice.postpractice.domain.member.dto;

/**
 * <br>package name   : practice.postpractice.domain.member.dto
 * <br>file name      : SignInRequestDto
 * <br>date           : 2024-08-22
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
 * 2024-08-22        SeungHoon              init create
 * </pre>
 */
public record SignInRequestDto(
        String username,
        String password
) {
}
