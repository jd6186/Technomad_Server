package technomad.api.server.technomad.api.plogging.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_PLOGGING")
public class PloggingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Comment("플로깅 고유번호")
    @Column(name = "PLOGGING_ID", nullable = false)
    private Long ploggingId;

    @Basic
    @Comment("승인 고유번호")
    @Setter
    @Column(name = "APPROVAL_ID", nullable = true)
    private Long approvalId;

    @Basic
    @Comment("플로깅 이동 위치 이미지")
    @Setter
    @Column(name = "WALKING_IMG_FILE_ID", nullable = true)
    private Long walkingImgFileId;

    @Basic
    @Comment("플로깅 걸음수")
    @Setter
    @Column(name = "WALKING_COUNT", nullable = true)
    private Integer walkingCount;

    @Basic
    @Comment("플로깅 수집 쓰레기 리터수")
    @Setter
    @Column(name = "TRASH_LITER", nullable = true)
    private Integer trashLiter;

    @Basic
    @Comment("플로깅 운동 거리")
    @Setter
    @Column(name = "EXERCISE_DISTANCE", nullable = true)
    private Integer exerciseDistance;

    @Basic
    @Comment("플로깅 타입 코드(솔로, 크루)")
    @Column(name = "WORK_TYPE_CODE", nullable = false, length = 1)
    private String workTypeCode;

    @Basic
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID", nullable = true)
    private Long crewId;

    @Basic
    @Comment("플로깅 시작일시 - 시작일이 곧 작업일")
    @Column(name = "START_DATETIME", nullable = false)
    private LocalDateTime startDatetime;

    @Basic
    @Comment("플로깅 종료일시")
    @Setter
    @Column(name = "END_DATETIME", nullable = true)
    private LocalDateTime endDatetime;

    @Basic
    @Comment("플로깅 상태(I:진행중, F: 종료)")
    @Setter
    @Column(name = "PLOGGING_STATUS", nullable = false, length = 1)
    private String ploggingStatus;

    @Basic
    @Comment("삭제 여부(Y/N)")
    @Column(name = "IS_DELETE", nullable = false, length = 1)
    @Setter
    private String isDelete;

    @Builder
    public PloggingEntity(Long ploggingId, Long approvalId, Long walkingImgFileId, Integer walkingCount, Integer trashLiter, Integer exerciseDistance, String workTypeCode, Long crewId, LocalDateTime startDatetime, LocalDateTime endDatetime, String ploggingStatus, String isDelete) {
        this.ploggingId = ploggingId;
        this.approvalId = approvalId;
        this.walkingImgFileId = walkingImgFileId;
        this.walkingCount = walkingCount;
        this.trashLiter = trashLiter;
        this.exerciseDistance = exerciseDistance;
        this.workTypeCode = workTypeCode;
        this.crewId = crewId;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.ploggingStatus = ploggingStatus;
        this.isDelete = isDelete;
    }
}
