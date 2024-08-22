package practice.postpractice.global.exception.exception;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import practice.postpractice.global.exception.errorCode.ErrorCode;

/**
 * <br>package name   : practice.postpractice.global.exception.exception
 * <br>file name      : JwtException
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
@Getter
public class JwtManageException extends JwtException {
    private final ErrorCode errorCode;

    public JwtManageException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public JwtManageException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
