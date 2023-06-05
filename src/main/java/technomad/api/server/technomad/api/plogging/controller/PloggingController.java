package technomad.api.server.technomad.api.plogging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technomad.api.server.technomad.api.plogging.code.PloggingStatusTyeCode;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.api.plogging.dto.request.*;
import technomad.api.server.technomad.api.plogging.dto.response.ApprovalImgAnalysisResponseDto;
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

    // TODO - 이미지 아이디 기반으로 이미 분석 진행
    @Operation(summary = "플로깅 인증 사진 분석 API", description = "플로깅 인증 사진 분석 API")
    @GetMapping("/approval-img-analysis")
    public ResponseEntity<TechnomadResponseDto<ApprovalImgAnalysisResponseDto>> imgAnalysis(ApprovalImgAnalysisRequestDto approvalImgAnalysisRequestDto){
        ApprovalImgAnalysisResponseDto approvalImgAnalysisResponseDto = ploggingService.getApprovalImgAnalysis();
        return TechnomadResponseDto.of(approvalImgAnalysisResponseDto);
    }

    @Operation(summary = "플로깅 인증 API", description = "플로깅 인증 API")
    @PostMapping("/approval")
    public ResponseEntity<TechnomadResponseDto<ApprovalEntity>> registerApproval(ApprovalRegisterRequestDto approvalRegisterRequestDto){
        ApprovalEntity approvalEntity = approvalRegisterRequestDto.getApprovalEntity();
        ApprovalEntity response = ploggingService.saveApproval(approvalEntity);
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "플로깅 인증 반려 API", description = "플로깅 인증 반려 API - 플로깅은 삭제 상태로 처리")
    @DeleteMapping("/approval")
    public ResponseEntity<TechnomadResponseDto<ApprovalEntity>> updateApproval(ApprovalDetailRequestDto approvalDetailRequestDto) throws Exception {
        // 연관된 플로깅 먼저 삭제 처리
        PloggingEntity ploggingEntity = ploggingService.getPloggingByApprovalId(approvalDetailRequestDto.getApprovalId());
        ploggingEntity.setPloggingStatus(PloggingStatusTyeCode.FINISH.getCode());
        ploggingEntity.setIsDelete("Y");
        PloggingEntity deletePlogging = ploggingService.savePlogging(ploggingEntity);
        if(deletePlogging == null) throw new Exception("None Data");

        // 인증 삭제 처리
        ApprovalEntity approvalEntity = ploggingService.getApprovalById(approvalDetailRequestDto.getApprovalId());
        approvalEntity.setIsDelete("Y");
        ApprovalEntity response = ploggingService.saveApproval(approvalEntity);
        return TechnomadResponseDto.of(response);
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
        ploggingUpdateRequestDto.getPloggingEntity(ploggingEntity);
        PloggingEntity response = ploggingService.savePlogging(ploggingEntity);
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "플로깅 취소 API(인증 반려 시)", description = "인증 실패 시 플로깅은 종료 후 삭제로 처리 API")
    @DeleteMapping
    public ResponseEntity<TechnomadResponseDto<PloggingEntity>> deletePlogging(PloggingDetailRequestDto ploggingDetailRequestDto){
        PloggingEntity ploggingEntity = ploggingService.getPloggingById(ploggingDetailRequestDto.getPloggingId());
        ploggingEntity.setPloggingStatus(PloggingStatusTyeCode.FINISH.getCode());
        ploggingEntity.setIsDelete("Y");
        PloggingEntity response = ploggingService.savePlogging(ploggingEntity);
        return TechnomadResponseDto.of(response);
    }
}
