package practice.postpractice.global.auth.jwt.domain;

import lombok.*;

/**
 * <br>package name   : practice.postpractice.global.auth.jwt
 * <br>file name      : JwtToken
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtToken {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}
