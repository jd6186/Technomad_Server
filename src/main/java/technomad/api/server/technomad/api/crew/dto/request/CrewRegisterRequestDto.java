package technomad.api.server.technomad.api.crew.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewRegisterRequestDto {
    @Schema(description = "등록 유저 고유번호", example = "1")
    private Long userId;
    @Schema(description = "크루명", example = "크루명입니다")
    private String crewName;
    @Schema(description = "크루 메인 이미지 파일 고유번호", example = "1")
    private Long crewMainImgFileId;
    @Schema(description = "크루 소개글", example = "크루 소개글입니다.")
    private String crewContent;
    @Schema(description = "크루 최대 정원수", example = "10")
    private Integer maxCount;
    @Schema(description = "핫크루여부(Y/N)", example = "Y")
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
