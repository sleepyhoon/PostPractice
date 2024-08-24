package practice.postpractice.global.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <br>package name   : practice.postpractice.global.auth.config
 * <br>file name      : CorsConfig
 * <br>date           : 2024-08-24
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
 * 2024-08-24        SeungHoon              init create
 * </pre>
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private static final String LOCAL_REACT_CLIENT_URL = "http://localhost:8080";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(LOCAL_REACT_CLIENT_URL).allowedMethods("*");
    }
}
