package technomad.api.server.technomad.api.common.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Common Controller", description = "Common 관리 컨트롤러")
@RestController
@RequestMapping("/common")
public class CommonController {
    // TODO - 초대 수락 API

    // TODO - 회원가입 API - 링크를 통한 회원가입 여부 체크

    // TODO - 로그인 API - 목표 쓰레기량을 작성했는지 구분자 값을 같이 내려줘야함
}
