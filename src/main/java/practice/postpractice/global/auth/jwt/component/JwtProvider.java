package practice.postpractice.global.auth.jwt.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import practice.postpractice.global.auth.jwt.domain.JwtToken;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * <br>package name   : practice.postpractice.global.auth
 * <br>file name      : JwtProvider
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
@Component
public class JwtProvider {
    private final Key key;
    private final long expireDateOfAccessToken;
    private final long expireDateOfRefreshToken;

    @Autowired
    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                       @Value("${jwt.expireDate.accessToken}") long expireDateOfAccessToken,
                       @Value("${jwt.expireDate.refreshToken}") long expireDateOfRefreshToken
                       ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expireDateOfAccessToken = expireDateOfAccessToken;
        this.expireDateOfRefreshToken = expireDateOfRefreshToken;
    }

    public JwtToken generateToken(Authentication authentication) {
        if(authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new IllegalArgumentException("인증이 안된 유저입니다.");
        }
        long now = new Date().getTime();
        String accessToken = generateAccessToken(authentication,now);
        String refreshToken = generateRefreshToken(now);

        return new JwtToken("Bearer",accessToken,refreshToken);
    }

    private String generateRefreshToken(long now) {
        Date tokenExpiration = new Date(now + expireDateOfRefreshToken);

        return Jwts.builder()
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(tokenExpiration)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    private String generateAccessToken(Authentication authentication, long now) {
        Date tokenExpiration = new Date(now + expireDateOfAccessToken);
        String authorities = getAuthoritiesFromAuthentication(authentication);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(tokenExpiration)
                .claim("auth",authorities)
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    private String getAuthoritiesFromAuthentication(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining("."));
    }
}
