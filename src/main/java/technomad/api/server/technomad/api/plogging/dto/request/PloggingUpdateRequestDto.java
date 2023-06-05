package technomad.api.server.technomad.api.plogging.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.plogging.code.PloggingStatusTyeCode;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class PloggingUpdateRequestDto {
    private Long ploggingId;
    private Long approvalId;
    private Long walkingImgFileId;
    private Integer walkingCount;
    private Integer trashLiter;
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
