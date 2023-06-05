package technomad.api.server.technomad.api.crew.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewFeedRegisterRequestDto {
    private Long crewId;
    private Long userId;
    private String feedTitle;
    private String feedContent;
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
