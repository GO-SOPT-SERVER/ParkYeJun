package sopt.org.FourthSeminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // 이 어노테이션이 있어야 변경 사항을 감지할 수 있다.
@SpringBootApplication
public class FourthSeminarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FourthSeminarApplication.class, args);
	}

}
