package technomad.api.server.technomad.api.invite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

@Tag(name = "Invite Controller", description = "Invite 관리 컨트롤러")
@RestController
@RequestMapping("/invite")
public class InviteController {
    // TODO - 작업 필요
    @Operation(summary = "크루 초대 > 작업일 부족으로 해당 기능은 보류", description = "크루 초대 API")
    @PostMapping("/crew")
    public ResponseEntity<TechnomadResponseDto<String>> inviteCrew() {
        return TechnomadResponseDto.of("보류");
    }
}
