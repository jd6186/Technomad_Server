package technomad.api.server.technomad.api.crew.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewRegisterRequestDto {
    private Long userId;
    private String crewName;
    private Long crewMainImgFileId;
    private String crewContent;
    private Integer maxCount;
    private String isHotCrew;

    public CrewEntity getCrewEntity(){
        return CrewEntity.builder()
                .crewName(crewName)
                .crewMainImgFileId(crewMainImgFileId)
                .crewContent(crewContent)
                .maxCount(maxCount)
                .isHotCrew(isHotCrew)
                .userId(userId)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .isDelete("N")
                .build();
    }
}
