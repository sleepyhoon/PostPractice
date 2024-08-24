package practice.postpractice.global.auth.jwt.component;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.NullJwtException;

import java.io.IOException;
import java.util.List;

/**
 * <br>package name   : practice.postpractice.global.auth.jwt
 * <br>file name      : JwtAuthenticationFilter
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
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtValidator jwtValidator;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private static final List<String> EXCLUDE_URLS = List.of(
            "/member/register",
            "/member/login",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    );

    private static final List<String> EXCLUDE_URL_PREFIXES = List.of(
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isExclude(url)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        try {
            String token = resolveToken((HttpServletRequest) servletRequest);
            if(token != null && jwtValidator.isTokenValid(token)) {
                Authentication authentication = jwtValidator.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new NullJwtException(ErrorCode.JWT_NULL);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (JwtException e) {
            if (e instanceof ExpiredJwtException) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else if (e instanceof SignatureException) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else if (e instanceof MalformedJwtException) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else if (e instanceof ClaimJwtException) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else if(e instanceof NullJwtException) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private String resolveToken(HttpServletRequest servletRequest) {
        String bearerToken = servletRequest.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean isExclude(String url) {
        // 정확한 매칭 확인
        for (String pattern : EXCLUDE_URLS) {
            if (pathMatcher.match(pattern, url)) {
                return true;
            }
        }
        // 접두어 매칭 확인
        return EXCLUDE_URL_PREFIXES.stream().anyMatch(url::startsWith);
    }
}
