package practice.postpractice.global.auth.jwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import practice.postpractice.global.auth.jwt.domain.RefreshToken;
import practice.postpractice.global.exception.errorCode.ErrorCode;
import practice.postpractice.global.exception.exception.JwtManageException;

import java.util.concurrent.TimeUnit;

/**
 * <br>package name   : practice.postpractice.global.auth.jwt.service
 * <br>file name      : RefreshTokenServiceImpl
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
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final StringRedisTemplate redisTemplate;
    private final String KEY_PREFIX = "refresh_token:";

    @Value("${jwt.expireDate.refreshToken}")
    private long timeOutMillis;

    @Override
    public void saveRefreshToken(RefreshToken token) {
        if(!StringUtils.hasText(token.getUsername()) || !StringUtils.hasText(token.getRefreshToken())) {
            throw new JwtManageException(ErrorCode.NOT_VALID_TOKEN);
        }
        try {
            redisTemplate.opsForValue().set(KEY_PREFIX + token.getUsername(),
                    token.getRefreshToken(), timeOutMillis, TimeUnit.MILLISECONDS);
        } catch (RedisConnectionFailureException e) {
            throw new RedisConnectionFailureException("connection fail while save token in redis");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid data");
        }

    }

    @Override
    public RefreshToken getRefreshToken(String username) {
        String token = redisTemplate.opsForValue().get(KEY_PREFIX + username);
        if(token != null) {
            return new RefreshToken(username, token);
        } else
            throw new JwtManageException(ErrorCode.NOT_VALID_TOKEN);
    }

    @Override
    public void deleteRefreshToken(String username) {
        redisTemplate.delete(KEY_PREFIX + username);
    }

    @Override
    public boolean isRefreshTokenValid(String username) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(KEY_PREFIX + username));
    }
}
