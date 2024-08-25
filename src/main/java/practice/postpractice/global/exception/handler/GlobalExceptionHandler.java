package practice.postpractice.global.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import practice.postpractice.global.exception.ErrorResponse;
import practice.postpractice.global.exception.exception.*;

/**
 * <br>package name   : practice.postpractice.global.exception.handler
 * <br>file name      : GloberHandler
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
@RestControllerAdvice
public class GlobalExceptionHandler {
    // JwtManageException 핸들링
    @ExceptionHandler(JwtManageException.class)
    public ResponseEntity<ErrorResponse> handleJwtManageException(JwtManageException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("JWT_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // LikeManagementException 핸들링
    @ExceptionHandler(LikeManagementException.class)
    public ResponseEntity<ErrorResponse> handleLikeManagementException(LikeManagementException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("LIKE_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // MemberManageException 핸들링
    @ExceptionHandler(MemberManageException.class)
    public ResponseEntity<ErrorResponse> handleMemberManageException(MemberManageException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("MEMBER_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // MovieManageException 핸들링
    @ExceptionHandler(MovieManageException.class)
    public ResponseEntity<ErrorResponse> handleMovieManageException(MovieManageException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("MOVIE_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // NullJwtException 핸들링
    @ExceptionHandler(NullJwtException.class)
    public ResponseEntity<ErrorResponse> handleNullJwtException(NullJwtException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("NULL_JWT_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // 기타 예외 핸들링 (선택 사항)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
