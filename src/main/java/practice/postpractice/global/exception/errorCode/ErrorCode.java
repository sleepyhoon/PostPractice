package practice.postpractice.global.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <br>package name   : practice.postpractice.global.exception.errorCode
 * <br>file name      : MemberErrorCode
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
@AllArgsConstructor
public enum ErrorCode {
    //member

    //jwt
    NOT_EXIST_AUTHENTICATION_IN_TOKEN(HttpStatus.UNAUTHORIZED,"인증을 찾을 수 없습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED,"username이나 refreshToken이 없습니다."),
    JWT_NULL(HttpStatus.UNAUTHORIZED,"인증 토큰이 없습니다."),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),


    ;
    private final HttpStatus httpStatus;
    private final String message;
}
