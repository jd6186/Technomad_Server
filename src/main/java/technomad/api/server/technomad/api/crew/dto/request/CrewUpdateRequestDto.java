package technomad.api.server.technomad.api.crew.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewUpdateRequestDto {
    private Long crewId;
    private String crewName;
    private Long crewMainImgFileId;
    private String crewContent;
    private Integer maxCount;
    private String isHotCrew;

    public void getUpdateCrewEntity(CrewEntity crewEntity){
        crewEntity.setCrewName(crewName);
        crewEntity.setCrewMainImgFileId(crewMainImgFileId);
        crewEntity.setCrewContent(crewContent);
        crewEntity.setMaxCount(maxCount);
        crewEntity.setIsHotCrew(isHotCrew);
        crewEntity.setUpdatedDatetime(CommonUtil.nowLocalDateTime());
    }
}
