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
@Table(name = "TB_APPROVAL")
public class ApprovalEntity {
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
    @Comment("종량제 쓰레기 수집 퍼센트")
    @Column(name = "GARBAGE_PICKUP_CAPACITY_PERCENTAGE_TYPE_CODE", nullable = false)
    private Integer garbagePickupCapacityPercentageTypeCode;
}
