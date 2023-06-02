package technomad.api.server.technomad.api.invite.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_APPROVAL")
public class ApprovalEntity {
    @Id
    @Column(name = "APPROVAL_ID", nullable = false)
    private Long approvalId;

    @Basic
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "APPROVAL_IMG_FILE_ID", nullable = false)
    private Long approvalImgFileId;

    @Basic
    @Column(name = "APPROVAL_DATETIME", nullable = false)
    private LocalDateTime approvalDatetime;

    @Basic
    @Column(name = "RUBBISH_BAG_LITER", nullable = false)
    private Integer rubbishBagLiter;

    @Basic
    @Column(name = "GARBAGE_PICKUP_CAPACITY_PERCENTAGE_TYPE_CODE", nullable = false)
    private Integer garbagePickupCapacityPercentageTypeCode;
}
