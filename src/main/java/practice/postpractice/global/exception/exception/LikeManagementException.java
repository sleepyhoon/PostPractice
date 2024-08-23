package practice.postpractice.global.exception.exception;

import lombok.Getter;
import practice.postpractice.global.exception.errorCode.ErrorCode;

/**
 * <br>package name   : practice.postpractice.global.exception.exception
 * <br>file name      : LikeManageMentException
 * <br>date           : 2024-08-23
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
 * 2024-08-23        SeungHoon              init create
 * </pre>
 */
@Getter
public class LikeManagementException extends RuntimeException {
    private final ErrorCode errorCode;

    public LikeManagementException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public LikeManagementException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
