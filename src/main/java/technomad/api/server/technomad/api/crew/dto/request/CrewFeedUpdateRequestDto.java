package technomad.api.server.technomad.api.crew.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewFeedUpdateRequestDto {
    @Schema(description = "크루 피드 고유번호", example = "1")
    private Long crewFeedId;
    @Schema(description = "피드 제목", example = "수정피드입니다")
    private String feedTitle;
    @Schema(description = "피드 내용", example = "수정된 피드 내용입니다.")
    private String feedContent;
    @Schema(description = "공지사항여부(Y/N)", example = "Y")
    private String isAnnouncement;

    public void getUpdateCrewFeedEntity(CrewFeedEntity crewFeedEntity){
        crewFeedEntity.setFeedContent(feedContent);
        crewFeedEntity.setFeedTitle(feedTitle);
        crewFeedEntity.setUpdatedDatetime(CommonUtil.nowLocalDateTime());
        crewFeedEntity.setIsAnnouncement(isAnnouncement);
    }
}
