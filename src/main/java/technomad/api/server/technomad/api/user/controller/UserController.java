package technomad.api.server.technomad.api.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;
import technomad.api.server.technomad.api.user.dto.request.UserDetailRequestDto;
import technomad.api.server.technomad.api.user.dto.request.UserSearchRequestDto;
import technomad.api.server.technomad.api.user.service.UserService;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

import java.util.List;

@Tag(name = "Announcement Controller", description = "Announcement 관리 컨트롤러")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "getUserDetailById", description = "유저 고유번호를 통한 상세조회")
    @GetMapping()
    public ResponseEntity<TechnomadResponseDto<UserEntity>> getUserDetailById(UserDetailRequestDto userDetailRequestDto){
        UserEntity response = userService.getUserDetailById(userDetailRequestDto.getUserId());
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "getSearchUser", description = "유저 목록 검색 조회")
    @GetMapping("/get-list")
    public ResponseEntity<TechnomadResponseDto<List<UserEntity>>> getUserDetailByAccountId(UserSearchRequestDto searchRequestDto){
        List<UserEntity> response = userService.getSearchUser(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }
}
