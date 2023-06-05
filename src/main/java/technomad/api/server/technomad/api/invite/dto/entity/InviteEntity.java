package technomad.api.server.technomad.api.invite.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_INVITE")
public class InviteEntity {
    @Id
    @Comment("초대 고유번호")
    @Column(name = "INVITE_ID", nullable = false)
    private Long inviteId;

    @Basic
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Comment("발신자 고유번호")
    @Column(name = "SENT_USER_ID", nullable = false)
    private Long sentUserId;

    @Basic
    @Comment("발신 URL")
    @Column(name = "SENT_URL", nullable = false, length = 2000)
    private String sentUrl;

    @Basic
    @Comment("발실일시")
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Comment("수신자 고유번호")
    @Column(name = "RECEPTION_USER_ID", nullable = true)
    private Long receptionUserId;

    @Basic
    @Comment("수신자 FCM 토큰")
    @Column(name = "RECEPTION_USER_FCM_TOKEN", nullable = true, columnDefinition = "text")
    private String receptionUserFcmToken;

    @Basic
    @Comment("수신자 앱 진입일시")
    @Column(name = "RECEPTION_DATETIME", nullable = true)
    private LocalDateTime receptionDatetime;

}
