package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private LocalDateTime updatedDatetime;

    @Basic
    @Comment("크루명")
    @Column(name = "CREW_NAME", length = 100, nullable = false)
    private String crewName;

    @Basic
    @Comment("크루 메인 이미지 고유번호")
    @Column(name = "CREW_MAIN_IMG_FILE_ID", nullable = true)
    private Long crewMainImgFileId;

    @Basic
    @Comment("크루 소개글")
    @Column(name = "CREW_CONTENT", columnDefinition = "text", nullable = false)
    private String crewContent;

    @Basic
    @Comment("크루 최대 인원")
    @Column(name = "MAX_COUNT", nullable = false)
    private Integer maxCount;
}
