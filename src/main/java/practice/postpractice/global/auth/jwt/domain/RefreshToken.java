package practice.postpractice.global.auth.jwt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * <br>package name   : practice.postpractice.global.auth.jwt
 * <br>file name      : RefreshToken
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
@AllArgsConstructor
@Getter
@Setter
public class RefreshToken {
    @Id
    private String username;

    private String refreshToken;
}
