package practice.postpractice.domain.member.dto;

/**
 * <br>package name   : practice.postpractice.domain.domain.member
 * <br>file name      : CreateMemberDto
 * <br>date           : 2024-08-21
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
 * 2024-08-21        SeungHoon              init create
 * </pre>
 */
public record CreateMemberDto(
        String username,
        String password,
        String nickname
) {
}
