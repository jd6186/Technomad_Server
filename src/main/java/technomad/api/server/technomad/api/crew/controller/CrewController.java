package technomad.api.server.technomad.api.crew.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Crew Controller", description = "Crew 관리 컨트롤러")
@RestController
@RequestMapping("/crew")
public class CrewController {
    // TODO - 핫한 크루목록 조회
    // TODO - 크루 상세 정보 조회
    // TODO - 크루 피드 목록 조회
    // TODO - 크루 인증 목록 조회
}
