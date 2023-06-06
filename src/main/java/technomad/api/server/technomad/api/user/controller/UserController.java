package technomad.api.server.technomad.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.user.dto.entity.UserCrewMappingEntity;
import technomad.api.server.technomad.api.user.dto.request.UserCrewMappingDetailRequestDto;
import technomad.api.server.technomad.api.user.dto.request.UserCrewMappingRegisterRequestDto;
import technomad.api.server.technomad.api.user.dto.request.UserDetailRequestDto;
import technomad.api.server.technomad.api.user.dto.response.UserTodayPloggingResponseDto;
import technomad.api.server.technomad.api.user.service.UserCrewMappingService;
import technomad.api.server.technomad.api.user.service.UserService;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;
import technomad.api.server.technomad.core.util.CommonUtil;

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
    public ResponseEntity<TechnomadResponseDto<UserTodayPloggingResponseDto>> myPlogging(UserDetailRequestDto userDetailRequestDto) {
        UserTodayPloggingResponseDto response = userService.getUserPloggingList(userDetailRequestDto.getUserId());
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "내가 가입한 크루 목록 API - 홈진입 시 호출", description = "내가 가입한 크루 목록 API")
    @GetMapping("/my-crew")
    public ResponseEntity<TechnomadResponseDto<List<CrewEntity>>> getMyCrew(UserDetailRequestDto userDetailRequestDto) {
        List<CrewEntity> response = userCrewMappingService.getUserCrewList(userDetailRequestDto.getUserId());
        return TechnomadResponseDto.of(response);
    }

    // 회원 크루 관리 ------------------------------------------------------------------------
    @Operation(summary = "크루 회원가입 API", description = "크루 회원가입 API")
    @PostMapping("/crew-member")
    public ResponseEntity<TechnomadResponseDto<UserCrewMappingEntity>> registerCrewMember(UserCrewMappingRegisterRequestDto userCrewMappingRegisterRequestDto) {
        UserCrewMappingEntity oldUserCrewMappingEntity = userCrewMappingService.getUserCrewMappingEntityByUserCrewMappingDetailRequestDto(userCrewMappingRegisterRequestDto);
        UserCrewMappingEntity response;
        if(oldUserCrewMappingEntity == null){
            UserCrewMappingEntity userCrewMappingEntity = userCrewMappingRegisterRequestDto.getUserCrewMappingEntity();
            response = userCrewMappingService.save(userCrewMappingEntity);
        } else {
            oldUserCrewMappingEntity.setIsDelete("N");
            oldUserCrewMappingEntity.setUpdatedDatetime(CommonUtil.nowLocalDateTime());
            response = userCrewMappingService.save(oldUserCrewMappingEntity);
        }
        return TechnomadResponseDto.of(response);
    }
    
    @Operation(summary = "크루 회원탈퇴 API", description = "크루 회원탈퇴 API")
    @DeleteMapping("/crew-member")
    public ResponseEntity<TechnomadResponseDto<UserCrewMappingEntity>> deleteCrewMember(UserCrewMappingDetailRequestDto userCrewMappingDetailRequestDto) {
        UserCrewMappingEntity userCrewMappingEntity = userCrewMappingService.getUserCrewMappingEntityById(userCrewMappingDetailRequestDto.getUserCrewMappingId());
        userCrewMappingEntity.setIsDelete("Y");
        userCrewMappingEntity.setUpdatedDatetime(CommonUtil.nowLocalDateTime());
        UserCrewMappingEntity response = userCrewMappingService.save(userCrewMappingEntity);
        return TechnomadResponseDto.of(response);
    }
}
