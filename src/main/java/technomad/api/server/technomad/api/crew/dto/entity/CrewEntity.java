package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW")
public class CrewEntity {
    @Id
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Comment("유저 마스터 고유번호")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Comment("크루 생성일시")
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Comment("크루 수정일시")
    @Column(name = "UPDATED_DATETIME", nullable = false)
    @Setter
    private LocalDateTime updatedDatetime;

    @Basic
    @Comment("크루명")
    @Column(name = "CREW_NAME", length = 100, nullable = false)
    @Setter
    private String crewName;

    @Basic
    @Comment("크루 메인 이미지 고유번호")
    @Column(name = "CREW_MAIN_IMG_FILE_ID", nullable = true)
    @Setter
    private Long crewMainImgFileId;

    @Basic
    @Comment("크루 소개글")
    @Column(name = "CREW_CONTENT", columnDefinition = "text", nullable = false)
    @Setter
    private String crewContent;

    @Basic
    @Comment("크루 최대 인원")
    @Column(name = "MAX_COUNT", nullable = false)
    @Setter
    private Integer maxCount;

    @Basic
    @Setter
    @Comment("크루 삭제여부(Y/N)")
    @Column(name = "IS_DELETE", nullable = false)
    private String isDelete;

    @Basic
    @Comment("핫크루 선정 여부(Y/N)")
    @Column(name = "IS_HOT_CREW", nullable = false)
    @Setter
    private String isHotCrew;

    @Builder
    public CrewEntity(Long crewId, Long userId, LocalDateTime createdDatetime, LocalDateTime updatedDatetime, String crewName, Long crewMainImgFileId, String crewContent, Integer maxCount, String isDelete, String isHotCrew) {
        this.crewId = crewId;
        this.userId = userId;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
        this.crewName = crewName;
        this.crewMainImgFileId = crewMainImgFileId;
        this.crewContent = crewContent;
        this.maxCount = maxCount;
        this.isDelete = isDelete;
        this.isHotCrew = isHotCrew;
    }
}
