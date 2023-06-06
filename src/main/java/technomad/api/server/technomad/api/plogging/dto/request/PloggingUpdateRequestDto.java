package technomad.api.server.technomad.api.plogging.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.plogging.code.PloggingStatusTyeCode;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class PloggingUpdateRequestDto {
    @Schema(description = "플로깅 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long ploggingId;
    @Schema(description = "플로깅 인증 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long approvalId;
    @Schema(description = "플로깅 이동경로 이미지 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long walkingImgFileId;
    @Schema(description = "플로깅 시 걸음수", example = "10000", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Integer walkingCount;
    @Schema(description = "플로깅 시 채운 쓰레기 리터수", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Integer trashLiter;
    @Schema(description = "플로깅 이동거리(단위: m)", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Integer exerciseDistance;

    public void getPloggingEntity(PloggingEntity ploggingEntity){
        ploggingEntity.setEndDatetime(CommonUtil.nowLocalDateTime());
        ploggingEntity.setApprovalId(approvalId);
        ploggingEntity.setWalkingImgFileId(walkingImgFileId);
        ploggingEntity.setWalkingCount(walkingCount);
        ploggingEntity.setTrashLiter(trashLiter);
        ploggingEntity.setExerciseDistance(exerciseDistance);
        ploggingEntity.setPloggingStatus(PloggingStatusTyeCode.FINISH.getCode());
    }
}
