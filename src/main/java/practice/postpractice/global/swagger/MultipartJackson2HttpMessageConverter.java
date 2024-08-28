package practice.postpractice.global.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * <br>package name   : practice.postpractice.global.swagger
 * <br>file name      : MultipartJackson2HttpMessageConverter
 * <br>date           : 2024-08-28
 * <pre>
 * <span style="color: white;">[description]</span>
 * <h3>Swagger에서 @ReqeustPart를 사용하여 MultiPartFile과 DTO 처리 시 Content type 'application/octet-stream' not supported 오류 해결</h3>
 *해당 클래스에서는 HTTP 응답 출력을 지원하지 않도록 canWrite() 메서드들을 오버라이드하여 모두 false를 반환하도록 설정되어 있습니다. 이로 인해 이 메시지 컨버터는 주로 HTTP 요청 데이터를 객체로 변환하는 데 사용됩니다.
 *
 * 위 코드를 Spring 프로젝트에 추가하고 @Component 어노테이션을 통해 빈으로 등록하면 Spring 컨텍스트에서 이 클래스의 인스턴스가 생성되어 관리됩니다.
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
 * 2024-08-28        SeungHoon              init create
 * </pre>
 */
@Component
public class MultipartJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    /**
     * "Content-Type: multipart/form-data" 헤더를 지원하는 HTTP 요청 변환기
     */
    public MultipartJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper, MediaType.APPLICATION_OCTET_STREAM);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    protected boolean canWrite(MediaType mediaType) {
        return false;
    }
}
