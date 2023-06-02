package technomad.api.server.technomad.api.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technomad.api.server.technomad.api.common.dto.request.LoginRequestDto;
import technomad.api.server.technomad.api.common.dto.response.LoginResponseDto;
import technomad.api.server.technomad.api.common.service.CommonService;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;
import technomad.api.server.technomad.api.user.service.UserCrewMappingService;
import technomad.api.server.technomad.api.user.service.UserService;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

import java.io.IOException;

@Tag(name = "Common Controller", description = "Common 관리 컨트롤러")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;
    private final UserService userService;
    private final UserCrewMappingService userCrewMappingService;
    public CommonController(CommonService commonService, UserService userService, UserCrewMappingService userCrewMappingService) {
        this.commonService = commonService;
        this.userService = userService;
        this.userCrewMappingService = userCrewMappingService;
    }

    @Operation(summary = "크루 초대 수락 API", description = "초대 수락 API - 크루 상세 페이지로 리다이렉트 필요")
    @GetMapping("/invite/access")
    public void inviteAccess(HttpServletResponse response) throws IOException {
        // TODO - 리다이렉트 시켜줄 URL 확인 필요 > 이 때 인증 처리는 어떻게 할 것인지 진짜 크루페이지 상세를 보여줄 것인지 아니면 별도 페이지를 분할할 것인지 결정 필요
        String redirect_uri="http://www.google.com";
        response.sendRedirect(redirect_uri);
    }

    @Operation(summary = "유저 로그인", description = "로그인 API - 링크를 통한 회원가입 여부 체크")
    @PostMapping("/login")
    public ResponseEntity<TechnomadResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        // 로그인할 유저 정보가 없다면 등록
        String accountId = loginRequestDto.getAccountId();
        UserEntity user = userService.getUserEntity(accountId);
        if(user == null){
            user = userService.registerUser(accountId);
        }

        // 링크 초대를 통해 들어온 유저 체크
        if(loginRequestDto.getCrewId() != null){
            userCrewMappingService.registerUserCrewMapping(user.getUserId(), loginRequestDto.getCrewId());
        }

        // 유저 로그인 시 체크할 요소 리턴
        LoginResponseDto response = commonService.getUserLoginData(accountId);
        return TechnomadResponseDto.of(response);
    }
}
