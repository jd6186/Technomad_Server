package technomad.api.server.technomad.config.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 생성자, 수정자 등을 자동으로 입력할 수 있게 지원하기 위해 제작된 클래스 절대 삭제 불가
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {
}