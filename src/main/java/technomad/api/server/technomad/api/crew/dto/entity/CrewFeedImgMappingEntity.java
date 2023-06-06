package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW_FEED_IMG_MAPPING")
public class CrewFeedImgMappingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Comment("크루 피드 고유번호")
    @Column(name = "CREW_FEED_ID")
    private Long crewFeedId;

    @Basic
    @Comment("피드 이미지 파일 고유번호")
    @Column(name = "FILE_ID")
    private Long fileId;
}
