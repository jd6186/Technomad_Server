package technomad.api.server.technomad.api.plogging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.api.plogging.dto.request.PloggingRegisterRequestDto;
import technomad.api.server.technomad.api.plogging.dto.request.PloggingUpdateRequestDto;
import technomad.api.server.technomad.api.plogging.service.PloggingService;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

@Tag(name = "Plogging Controller", description = "Plogging 관리 컨트롤러")
@RestController
@RequestMapping("/plogging")
public class PloggingController {
    private final PloggingService ploggingService;
    public PloggingController(PloggingService ploggingService) {
        this.ploggingService = ploggingService;
    }
    @Operation(summary = "플로깅 등록 API", description = "플로깅 등록 API")
    @PostMapping
    public ResponseEntity<TechnomadResponseDto<PloggingEntity>> registerPlogging(PloggingRegisterRequestDto ploggingRegisterRequestDto){
        PloggingEntity ploggingEntity = ploggingRegisterRequestDto.getPloggingEntity();
        PloggingEntity response = ploggingService.savePlogging(ploggingEntity);
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "플로깅 인증 성공 시 플로깅 이력 수정 API", description = "인증 완료 후 로컬에 보관된 플로깅 이력을 저장할 API")
    @PutMapping
    public ResponseEntity<TechnomadResponseDto<PloggingEntity>> updatePlogging(PloggingUpdateRequestDto ploggingUpdateRequestDto){
        PloggingEntity ploggingEntity = ploggingService.getPloggingById(ploggingUpdateRequestDto.getPloggingId());
        PloggingEntity response = ploggingService.savePlogging(ploggingEntity);
        return TechnomadResponseDto.of(response);
    }

    // TODO - 작업 필요
    @Operation(summary = "플로깅 인증 API"
            , description = "플로깅 인증 API - 인증 완료 시 플로깅은 종료 상태로 변경, 인증 실패 시 플로깅은 종료 후 삭제로 처리")
    @PostMapping("/approval")
    public ResponseEntity<TechnomadResponseDto<ApprovalEntity>> approvalPlogging(PloggingRegisterRequestDto ploggingRegisterRequestDto){
        ApprovalEntity approvalEntity = null;
        ApprovalEntity response = ploggingService.saveApproval(approvalEntity);
        return TechnomadResponseDto.of(response);
    }

    // TODO - 플로깅 인증 이력 삭제 - 인증 삭제 시 플로깅 이력도 삭제로 변경
}
