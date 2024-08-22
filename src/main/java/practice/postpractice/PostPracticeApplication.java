package practice.postpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PostPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostPracticeApplication.class, args);
    }

}
