package technomad.api.server.technomad.api.invite.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_INVITE")
public class InviteEntity {
    @Id
    @Column(name = "INVITE_ID", nullable = false)
    private Long inviteId;

    @Basic
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Column(name = "SENT_USER_ID", nullable = false)
    private Long sentUserId;

    @Basic
    @Column(name = "SENT_URL", nullable = false, length = 2000)
    private String sentUrl;

    @Basic
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Column(name = "RECEPTION_USER_ID", nullable = true)
    private Long receptionUserId;

    @Basic
    @Column(name = "RECEPTION_USER_FCM_TOKEN", nullable = true, columnDefinition = "text")
    private String receptionUserFcmToken;

    @Basic
    @Column(name = "RECEPTION_DATETIME", nullable = true)
    private LocalDateTime receptionDatetime;

}
