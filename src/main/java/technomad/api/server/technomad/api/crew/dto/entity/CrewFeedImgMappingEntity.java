package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW_FEED_IMG_MAPPING")
public class CrewFeedImgMappingEntity {
    @Id
    @Column(name = "CREW_FEED_ID")
    private Long crewFeedId;

    @Basic
    @Column(name = "FILE_ID")
    private Long fileId;

    @Basic
    @Column(name = "USER_ID")
    private Long userId;
}
