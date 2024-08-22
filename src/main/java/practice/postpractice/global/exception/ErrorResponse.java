package practice.postpractice.global.exception;

import practice.postpractice.global.exception.errorCode.ErrorCode;

/**
 * <br>package name   : practice.postpractice.global.exception
 * <br>file name      : ErrorResponse
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
public record ErrorResponse(
        String errorCode,
        String errorMessage
) {
    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getHttpStatus().name(),errorCode.getMessage());
    }
}
