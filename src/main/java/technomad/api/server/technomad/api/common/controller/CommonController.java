package technomad.api.server.technomad.api.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technomad.api.server.technomad.api.common.dto.request.LoginRequestDto;
import technomad.api.server.technomad.api.common.service.CommonService;
import technomad.api.server.technomad.api.user.dto.response.UserDetailResponseDto;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

import java.io.IOException;

@Tag(name = "Common Controller", description = "Common 관리 컨트롤러")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @Operation(summary = "크루 초대 수락 API", description = "초대 수락 API - 크루 상세 페이지로 리다이렉트 필요")
    @GetMapping("/invite/access")
    public void inviteAccess(HttpServletResponse response) throws IOException {
        // TODO - 리다이렉트 시켜줄 URL 확인 필요 > 이 때 인증 처리는 어떻게 할 것인지 진짜 크루페이지 상세를 보여줄 것인지 아니면 별도 페이지를 분할할 것인지 결정 필요
        String redirect_uri="http://www.google.com";
        response.sendRedirect(redirect_uri);
    }

    @Operation(summary = "유저 로그인", description = "회원가입 API - 링크를 통한 회원가입 여부 체크")
    @PostMapping("/login")
    public ResponseEntity<TechnomadResponseDto<UserDetailResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        // TODO - 로그인 후 토큰 발급 필요
        UserDetailResponseDto response = null;
        return TechnomadResponseDto.of(response);
    }
}
