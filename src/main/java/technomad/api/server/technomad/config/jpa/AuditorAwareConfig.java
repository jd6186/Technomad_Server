package technomad.api.server.technomad.config.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 생성자, 수정자 등을 자동으로 입력할 수 있게 지원하기 위해 제작된 클래스 절대 삭제 불가
 */
@Component
public class AuditorAwareConfig implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return Optional.of(0L);
        }
        // TODO - 개발 완료 후 아래 내용 바꾸기
//        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
//        Long userId = userEntity.getUserId();
        Long userId = 1L;
        return Optional.ofNullable(userId);
    }
}