package technomad.api.server.technomad.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.user.dto.request.UserDetailRequestDto;
import technomad.api.server.technomad.api.user.dto.response.UserTodayPloggingHistoryResponseDto;
import technomad.api.server.technomad.api.user.service.UserCrewMappingService;
import technomad.api.server.technomad.api.user.service.UserService;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

import java.util.List;

@Tag(name = "User Controller", description = "User 관리 컨트롤러")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserCrewMappingService userCrewMappingService;
    public UserController(UserService userService, UserCrewMappingService userCrewMappingService) {
        this.userService = userService;
        this.userCrewMappingService = userCrewMappingService;
    }

    @Operation(summary = "나의 활동이력 API - 홈진입 시 호출", description = "나의 활동이력 API")
    @GetMapping("/my-plogging-history")
    public ResponseEntity<TechnomadResponseDto<UserTodayPloggingHistoryResponseDto>> myPloggingHistory(UserDetailRequestDto userDetailRequestDto) {
        UserTodayPloggingHistoryResponseDto response = userService.getUserPloggingHistoryList(userDetailRequestDto.getUserId());
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "내가 가입한 크루 목록 API - 홈진입 시 호출", description = "내가 가입한 크루 목록 API")
    @GetMapping("/my-crew")
    public ResponseEntity<TechnomadResponseDto<List<CrewEntity>>> getMyCrew(UserDetailRequestDto userDetailRequestDto) {
        List<CrewEntity> response = userCrewMappingService.getUserCrewList(userDetailRequestDto.getUserId());
        return TechnomadResponseDto.of(response);
    }
}
