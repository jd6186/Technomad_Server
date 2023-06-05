package technomad.api.server.technomad.api.plogging.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plogging Controller", description = "Plogging 관리 컨트롤러")
@RestController
@RequestMapping("/plogging")
public class PloggingController {
    // TODO - 플로깅 인증 기능 - 인증 완료 시 플로깅은 종료 상태로 변경
    // TODO - 로컬에 쌓인 플로깅 이력 저장 기능
    // TODO - 플로깅 인증 이력 삭제
}
