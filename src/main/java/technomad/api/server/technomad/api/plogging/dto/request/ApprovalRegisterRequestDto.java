package technomad.api.server.technomad.api.plogging.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class ApprovalRegisterRequestDto {
    @Schema(description = "인증 시도 유저 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long userId;
    @Schema(description = "인증 종량제 봉투 이미지 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long approvalImgFileId;
    @Schema(description = "종량제 봉투 총 리터수", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Integer rubbishBagLiter;
    @Schema(description = "종량제 봉투 기준 쓰레기 수집 퍼센트 타입 코드", example = "03", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String garbagePickupCapacityPercentageTypeCode;

    public ApprovalEntity getApprovalEntity(){
        return ApprovalEntity
                .builder()
                .userId(userId)
                .approvalImgFileId(approvalImgFileId)
                .rubbishBagLiter(rubbishBagLiter)
                .garbagePickupCapacityPercentageTypeCode(garbagePickupCapacityPercentageTypeCode)
                .approvalDatetime(CommonUtil.nowLocalDateTime())
                .isDelete("N")
                .build();
    }
}
