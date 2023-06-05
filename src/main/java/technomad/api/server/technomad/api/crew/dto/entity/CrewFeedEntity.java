package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW_FEED")
public class CrewFeedEntity {
    @Id
    @Comment("크루 피드 고유번호")
    @Column(name = "CREW_FEED_ID", nullable = false)
    private Long crewFeedId;

    @Basic
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Comment("작성자 고유번호")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Comment("피드 제목")
    @Column(name = "FEED_TITLE", nullable = false, length = 255)
    @Setter
    private String feedTitle;

    @Basic
    @Comment("피드 내용")
    @Column(name = "FEED_CONTENT", nullable = false, columnDefinition = "text")
    @Setter
    private String feedContent;

    @Basic
    @Comment("피드 생성일시")
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Comment("피드 수정일시")
    @Column(name = "UPDATED_DATETIME", nullable = false)
    @Setter
    private LocalDateTime updatedDatetime;

    @Basic
    @Comment("공지글 여부(Y/N)")
    @Column(name = "IS_ANNOUNCEMENT", nullable = false, length = 1)
    @Setter
    private String isAnnouncement;

    @Basic
    @Comment("삭제 여부(Y/N)")
    @Column(name = "IS_DELETE", nullable = false, length = 1)
    @Setter
    private String isDelete;

    @Builder
    public CrewFeedEntity(Long crewFeedId, Long crewId, Long userId, String feedTitle, String feedContent, LocalDateTime createdDatetime, LocalDateTime updatedDatetime, String isAnnouncement, String isDelete) {
        this.crewFeedId = crewFeedId;
        this.crewId = crewId;
        this.userId = userId;
        this.feedTitle = feedTitle;
        this.feedContent = feedContent;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
        this.isAnnouncement = isAnnouncement;
        this.isDelete = isDelete;
    }
}
