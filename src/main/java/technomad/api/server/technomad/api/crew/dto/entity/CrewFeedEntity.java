package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW_FEED")
public class CrewFeedEntity {
    @Id
    @Column(name = "CREW_FEED_ID", nullable = false)
    private Long crewFeedId;

    @Basic
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "FEED_TITLE", nullable = false, length = 255)
    private String feedTitle;

    @Basic
    @Column(name = "FEED_CONTENT", nullable = false, columnDefinition = "text")
    private String feedContent;

    @Basic
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Column(name = "UPDATED_DATETIME", nullable = false)
    private LocalDateTime updatedDatetime;

    @Basic
    @Column(name = "IS_ANNOUNCEMENT", nullable = false, length = 1)
    private String isAnnouncement;
}
