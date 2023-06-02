package technomad.api.server.technomad.api.user.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_USER")
public class UserEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Column(name = "UPDATED_DATETIME", nullable = false)
    private LocalDateTime updatedDatetime;

    @Basic
    @Column(name = "FCM_TOKEN", nullable = true, columnDefinition = "text")
    private String fcmToken;

    @Basic
    @Column(name = "USER_STATUS_CODE", nullable = false)
    private String userStatusCode;

    @Basic
    @Column(name = "USER_IMG_FILE_ID", nullable = true)
    private Long userImgFileId;

    @Basic
    @Column(name = "TARGET_TRASH_LITER", nullable = true)
    private Integer targetTrashLiter;

    @Basic
    @Column(name = "NICKNAME", nullable = true)
    private String nickname;

    @Basic
    @Column(name = "ACCOUNT_ID", nullable = false)
    private String accountId;
}
