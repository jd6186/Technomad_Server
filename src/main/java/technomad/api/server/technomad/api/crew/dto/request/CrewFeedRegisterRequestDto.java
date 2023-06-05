package technomad.api.server.technomad.api.crew.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewFeedRegisterRequestDto {
    @Schema(description = "크루 고유번호", example = "1")
    private Long crewId;
    @Schema(description = "유저 고유번호", example = "1")
    private Long userId;
    @Schema(description = "피드 제목", example = "피드 제목입니다")
    private String feedTitle;
    @Schema(description = "피드 내용", example = "피드 내용입니다.")
    private String feedContent;
    @Schema(description = "공지사항여부(Y/N)", example = "Y")
    private String isAnnouncement;

    public CrewFeedEntity getCrewFeedEntity(){
        return CrewFeedEntity
                .builder()
                .crewId(crewId)
                .userId(userId)
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .isAnnouncement(isAnnouncement)
                .isDelete("N")
                .build();
    }
}
