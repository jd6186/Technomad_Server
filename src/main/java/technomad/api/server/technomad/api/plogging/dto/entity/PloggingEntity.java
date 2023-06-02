package technomad.api.server.technomad.api.plogging.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_PLOGGING")
public class PloggingEntity {
    @Id
    @Column(name = "PLOGGING_ID", nullable = false)
    private Long ploggingId;

    @Basic
    @Column(name = "APPROVAL_ID", nullable = false)
    private Long approvalId;

    @Basic
    @Column(name = "WALKING_IMG_FILE_ID", nullable = false)
    private Long walkingImgFileId;

    @Basic
    @Column(name = "WALKING_COUNT", nullable = false)
    private Integer walkingCount;

    @Basic
    @Column(name = "TRASH_LITER", nullable = false)
    private Integer trashLiter;

    @Basic
    @Column(name = "EXERCISE_DISTANCE", nullable = false)
    private Integer exerciseDistance;

    @Basic
    @Column(name = "WORK_TYPE_CODE", nullable = false, length = 1)
    private String workTypeCode;

    @Basic
    @Column(name = "START_DATETIME", nullable = false)
    private LocalDateTime startDatetime;

    @Basic
    @Column(name = "END_DATETIME", nullable = false)
    private LocalDateTime endDatetime;
}
