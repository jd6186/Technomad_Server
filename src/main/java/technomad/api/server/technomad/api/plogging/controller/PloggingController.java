package technomad.api.server.technomad.api.plogging.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plogging Controller", description = "Plogging 관리 컨트롤러")
@RestController
@RequestMapping("/plogging")
public class PloggingController {
    // TODO - 현재 플로깅을 하고 있는 유저수 조회
    // TODO - 플로깅 인증 기능
    // TODO - 로컬에 쌓인 플로깅 이력 저장 기능
}
