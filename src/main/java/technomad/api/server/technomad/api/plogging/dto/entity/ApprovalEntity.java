package technomad.api.server.technomad.api.plogging.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_APPROVAL")
public class ApprovalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Comment("인증 고유번호")
    @Column(name = "APPROVAL_ID", nullable = false)
    private Long approvalId;

    @Basic
    @Comment("유저 고유번호")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Comment("인증 이미지 고유번호")
    @Column(name = "APPROVAL_IMG_FILE_ID", nullable = false)
    private Long approvalImgFileId;

    @Basic
    @Comment("인증일시")
    @Column(name = "APPROVAL_DATETIME", nullable = false)
    private LocalDateTime approvalDatetime;

    @Basic
    @Comment("종량제 리터수")
    @Column(name = "RUBBISH_BAG_LITER", nullable = false)
    private Integer rubbishBagLiter;

    @Basic
    @Comment("종량제 쓰레기 수집 퍼센트(01: 0%, 02: 25%, 03: 50%, 04: 75%, 05: 100%)")
    @Column(name = "GARBAGE_PICKUP_CAPACITY_PERCENTAGE_TYPE_CODE", nullable = false, length = 2)
    private String garbagePickupCapacityPercentageTypeCode;

    @Basic
    @Comment("삭제 여부(Y/N)")
    @Column(name = "IS_DELETE", nullable = false, length = 1)
    @Setter
    private String isDelete;

    @Builder
    public ApprovalEntity(Long approvalId, Long userId, Long approvalImgFileId, LocalDateTime approvalDatetime, Integer rubbishBagLiter, String garbagePickupCapacityPercentageTypeCode, String isDelete) {
        this.approvalId = approvalId;
        this.userId = userId;
        this.approvalImgFileId = approvalImgFileId;
        this.approvalDatetime = approvalDatetime;
        this.rubbishBagLiter = rubbishBagLiter;
        this.garbagePickupCapacityPercentageTypeCode = garbagePickupCapacityPercentageTypeCode;
        this.isDelete = isDelete;
    }
}
