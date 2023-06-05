package technomad.api.server.technomad.api.plogging.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApprovalImgAnalysisResponseDto {
    @Schema(description = "종량제 봉투 총 리터수", example = "1")
    private final Integer rubbishBagLiter;
    @Schema(description = "종량제봉투 기준 쓰레기 수집 퍼센트 타입코드", example = "03")
    private final String garbagePickupCapacityPercentageTypeCode;
    @Schema(description = "종량제봉투 기준 쓰레기 수집 퍼센트 타입코드 설명", example = "50%")
    private final String garbagePickupCapacityPercentageTypeDescription;

    @Builder
    public ApprovalImgAnalysisResponseDto(Integer rubbishBagLiter, String garbagePickupCapacityPercentageTypeCode, String garbagePickupCapacityPercentageTypeDescription) {
        this.rubbishBagLiter = rubbishBagLiter;
        this.garbagePickupCapacityPercentageTypeCode = garbagePickupCapacityPercentageTypeCode;
        this.garbagePickupCapacityPercentageTypeDescription = garbagePickupCapacityPercentageTypeDescription;
    }
}
