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
public class PloggingRegisterRequestDto {
    @Schema(description = "S: 솔로, C: 크루", example = "C", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String workTypeCode;
    @Schema(description = "크루로 플로깅 시 크루 고유번호", example = "1")
    private Long crewId;
    public PloggingEntity getPloggingEntity(){
        return PloggingEntity
                .builder()
                .workTypeCode(workTypeCode)
                .crewId(crewId)
                .ploggingStatus(PloggingStatusTyeCode.ING.getCode())
                .startDatetime(CommonUtil.nowLocalDateTime())
                .isDelete("N")
                .build();
    }
}
