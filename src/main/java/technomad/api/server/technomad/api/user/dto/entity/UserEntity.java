package technomad.api.server.technomad.api.user.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_USER")
public class UserEntity {
    @Id
    @Comment("유저 고유번호")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Comment("유저 생성일시")
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Comment("유저 수정일시")
    @Column(name = "UPDATED_DATETIME", nullable = false)
    private LocalDateTime updatedDatetime;

    @Basic
    @Comment("유저 FCM Token")
    @Column(name = "FCM_TOKEN", nullable = true, columnDefinition = "text")
    private String fcmToken;

    @Basic
    @Comment("유저 상태 - N: 일반, D: 휴면, W:탈퇴")
    @Column(name = "USER_STATUS_CODE", nullable = false)
    private String userStatusCode;

    @Basic
    @Comment("유저 프로필이미지")
    @Column(name = "USER_IMG_FILE_ID", nullable = true)
    private Long userImgFileId;

    @Basic
    @Comment("유저 목표 쓰레기 수집량")
    @Column(name = "TARGET_TRASH_LITER", nullable = true)
    private Integer targetTrashLiter;

    @Basic
    @Comment("유저 닉네")
    @Column(name = "NICKNAME", nullable = true)
    private String nickname;

    @Basic
    @Comment("유저 계정 ID")
    @Column(name = "ACCOUNT_ID", nullable = false)
    private String accountId;

    @Builder
    public UserEntity(Long userId, LocalDateTime createdDatetime, LocalDateTime updatedDatetime, String fcmToken, String userStatusCode, Long userImgFileId, Integer targetTrashLiter, String nickname, String accountId) {
        this.userId = userId;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
        this.fcmToken = fcmToken;
        this.userStatusCode = userStatusCode;
        this.userImgFileId = userImgFileId;
        this.targetTrashLiter = targetTrashLiter;
        this.nickname = nickname;
        this.accountId = accountId;
    }
}
