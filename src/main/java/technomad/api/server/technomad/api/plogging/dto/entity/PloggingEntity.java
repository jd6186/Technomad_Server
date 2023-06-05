package technomad.api.server.technomad.api.plogging.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_PLOGGING")
public class PloggingEntity {
    @Id
    @Comment("플로깅 고유번호")
    @Column(name = "PLOGGING_ID", nullable = false)
    private Long ploggingId;

    @Basic
    @Comment("승인 고유번호")
    @Column(name = "APPROVAL_ID", nullable = false)
    private Long approvalId;

    @Basic
    @Comment("플로깅 이동 위치 이미지")
    @Column(name = "WALKING_IMG_FILE_ID", nullable = false)
    private Long walkingImgFileId;

    @Basic
    @Comment("플로깅 걸음수")
    @Column(name = "WALKING_COUNT", nullable = false)
    private Integer walkingCount;

    @Basic
    @Comment("플로깅 수집 쓰레기 리터수")
    @Column(name = "TRASH_LITER", nullable = false)
    private Integer trashLiter;

    @Basic
    @Comment("플로깅 운동 거리")
    @Column(name = "EXERCISE_DISTANCE", nullable = false)
    private Integer exerciseDistance;

    @Basic
    @Comment("플로깅 타입 코드(솔로, 크루)")
    @Column(name = "WORK_TYPE_CODE", nullable = false, length = 1)
    private String workTypeCode;

    @Basic
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Comment("플로깅 시작일시 - 시작일이 곧 작업일")
    @Column(name = "START_DATETIME", nullable = false)
    private LocalDateTime startDatetime;

    @Basic
    @Comment("플로깅 종료일시")
    @Column(name = "END_DATETIME", nullable = false)
    private LocalDateTime endDatetime;

    @Basic
    @Comment("삭제 여부(Y/N)")
    @Column(name = "IS_DELETE", nullable = false, length = 1)
    private String isDelete;
}
