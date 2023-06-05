package technomad.api.server.technomad.api.plogging.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.plogging.code.PloggingStatusTyeCode;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class PloggingRegisterRequestDto {
    private String workTypeCode;
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
