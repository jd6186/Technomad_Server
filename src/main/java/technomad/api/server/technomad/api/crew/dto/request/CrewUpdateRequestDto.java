package technomad.api.server.technomad.api.crew.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewUpdateRequestDto {
    @Schema(description = "크루 고유번호", example = "1")
    private Long crewId;
    @Schema(description = "크루명", example = "수정된크루명입니다")
    private String crewName;
    @Schema(description = "크루 메인 이미지 파일 고유번호", example = "1")
    private Long crewMainImgFileId;
    @Schema(description = "크루 소개글", example = "수정된 크루 소개글 입니다.")
    private String crewContent;
    @Schema(description = "크루 최대정원수", example = "20")
    private Integer maxCount;
    @Schema(description = "핫크루여부(Y/N)", example = "Y")
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
