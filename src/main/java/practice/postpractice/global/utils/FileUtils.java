package practice.postpractice.global.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class FileUtils {
    private static final String UPLOAD_DIR = "uploads";

    private FileUtils() {}

    public static String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename;
        Path filePath;

        try {
            // 1. 원본 파일명 가져오기
            if (originalFilename == null) {
                throw new IOException("파일 이름이 존재하지 않습니다.");
            }

            // 2. 고유한 파일명 생성 (UUID + 원본 파일 확장자)
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            uniqueFilename = UUID.randomUUID() + extension;

            // 3. 파일 저장 경로 생성
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 4. 파일 저장
            filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("파일 저장 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 저장 실패", e);
        } catch (Exception e) {
            log.error("예상치 못한 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 처리 중 예상치 못한 오류가 발생했습니다.", e);
        }

        // 5. 저장된 파일 경로 반환
        return filePath.toString();
    }

    public static void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            log.info("파일 삭제 성공: {}", filePath);
        } catch (IOException e) {
            log.error("파일 삭제 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("파일 삭제 실패", e);
        }
    }
}
