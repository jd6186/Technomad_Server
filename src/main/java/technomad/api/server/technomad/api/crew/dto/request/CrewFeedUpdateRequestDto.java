package technomad.api.server.technomad.api.crew.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class CrewFeedUpdateRequestDto {
    private Long crewFeedId;
    private String feedTitle;
    private String feedContent;
    private String isAnnouncement;

    public void getUpdateCrewFeedEntity(CrewFeedEntity crewFeedEntity){
        crewFeedEntity.setFeedContent(feedContent);
        crewFeedEntity.setFeedTitle(feedTitle);
        crewFeedEntity.setUpdatedDatetime(CommonUtil.nowLocalDateTime());
        crewFeedEntity.setIsAnnouncement(isAnnouncement);
    }
}
