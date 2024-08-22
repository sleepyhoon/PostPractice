package practice.postpractice.global.exception.exception;

import io.jsonwebtoken.JwtException;
import practice.postpractice.global.exception.errorCode.ErrorCode;

/**
 * <br>package name   : practice.postpractice.global.auth.jwt
 * <br>file name      : NullJwtException
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
public class NullJwtException extends JwtException {
    private final ErrorCode errorCode;

    public NullJwtException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public NullJwtException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
