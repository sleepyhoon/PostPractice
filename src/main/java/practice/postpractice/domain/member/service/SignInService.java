package practice.postpractice.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import practice.postpractice.domain.member.dto.SignInRequestDto;
import practice.postpractice.global.auth.jwt.component.JwtProvider;
import practice.postpractice.global.auth.jwt.component.JwtValidator;
import practice.postpractice.global.auth.jwt.domain.JwtToken;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.JwtManageException;
import practice.postpractice.global.exception.exception.MemberManageException;

/**
 * <br>package name   : practice.postpractice.domain.member.service
 * <br>file name      : SignInService
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
@Service
@RequiredArgsConstructor
public class SignInService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final JwtValidator jwtValidator;


    public JwtToken SignIn(SignInRequestDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            return jwtProvider.generateToken(authenticate);
        } catch (BadCredentialsException e) {
            // 잘못된 사용자 이름 또는 비밀번호
            throw new MemberManageException(ErrorCode.CREDENTIAL_INVALID);
        } catch (LockedException e) {
            // 계정이 잠긴 경우
            throw new MemberManageException(ErrorCode.LOCK_ACCOUNT);
        } catch (DisabledException e) {
            // 계정이 비활성화된 경우
            throw new MemberManageException(ErrorCode.DISABLED_ACCOUNT);
        } catch (AccountExpiredException e) {
            // 계정이 만료된 경우
            throw new MemberManageException(ErrorCode.EXPIRED_ACCOUNT);
        } catch (CredentialsExpiredException e) {
            // 비밀번호가 만료된 경우
            throw new MemberManageException(ErrorCode.CREDENTIAL_EXPIRED_ACCOUNT);
        } catch (InternalAuthenticationServiceException e) {
            // 내부 인증 서비스 오류
            throw new MemberManageException(ErrorCode.INTERNAL_AUTHENTICATION_ERROR);
        }
    }

    public Authentication getAuthenticationFromHeader(String header) {
        if(!(header != null && header.startsWith("Bearer "))) {
            throw new MemberManageException(ErrorCode.NOT_VALID_TOKEN);
        }
        String accessToken = header.substring(7);
        if(!jwtValidator.isTokenValid(accessToken)){
            throw new JwtManageException(ErrorCode.NOT_VALID_TOKEN);
        }
        return jwtValidator.getAuthentication(accessToken);
    }
}
