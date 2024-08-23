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
    DUPLICATE_USER(HttpStatus.CONFLICT, "중복된 유저입니다"),
    NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다."),

    //login
    CREDENTIAL_INVALID(HttpStatus.UNAUTHORIZED, "아이디나 비밀번호가 일치하지 않습니다."),
    LOCK_ACCOUNT(HttpStatus.LOCKED, "잠긴 계정입니다."),
    DISABLED_ACCOUNT(HttpStatus.FORBIDDEN, "사용 불가능한 계정입니다."),
    EXPIRED_ACCOUNT(HttpStatus.FORBIDDEN, "만료된 계정입니다."),
    CREDENTIAL_EXPIRED_ACCOUNT(HttpStatus.FORBIDDEN, "계정 인증이 만료되었습니다."),
    INTERNAL_AUTHENTICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부적으로 인증 관련 문제가 있습니다."),

    //jwt
    NOT_EXIST_AUTHENTICATION_IN_TOKEN(HttpStatus.UNAUTHORIZED,"인증을 찾을 수 없습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED,"username이나 refreshToken이 없습니다."),
    JWT_NULL(HttpStatus.UNAUTHORIZED,"인증 토큰이 없습니다."),

    // movie
    DUPLICATE_MOVIE(HttpStatus.CONFLICT, "중복된 id를 가진 영화입니다."),
    NOT_EXIST_MOVIE(HttpStatus.NOT_FOUND,"해당 id를 가진 영화가 없습니다."),


    // like
    DUPLICATE_LIKE(HttpStatus.CONFLICT,"이미 좋아요를 눌렀습니다."),


    // 기타
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),


    ;
    private final HttpStatus httpStatus;
    private final String message;
}
