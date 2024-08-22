package practice.postpractice.global.auth.jwt.service;

import practice.postpractice.global.auth.jwt.domain.RefreshToken;

/**
 * <br>package name   : practice.postpractice.global.auth.jwt.service
 * <br>file name      : RefreshTokenService
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
public interface RefreshTokenService {
    void saveRefreshToken(RefreshToken refreshToken);
    RefreshToken getRefreshToken(String username);
    void deleteRefreshToken(String username);
    boolean isRefreshTokenValid(String username);
}
