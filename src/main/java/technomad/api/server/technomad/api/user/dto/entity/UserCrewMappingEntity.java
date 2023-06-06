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
@Table(name = "TB_USER_CREW_MAPPING")
public class UserCrewMappingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Comment("유저 크루 맵핑 고유번호")
    @Column(name = "USER_CREW_MAPPING_ID", nullable = false)
    private Long userCrewMappingId;

    @Basic
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Comment("유저 고유번호")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Comment("맵핑 생성일시")
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Comment("맵핑 수정일시")
    @Column(name = "UPDATED_DATETIME", nullable = false)
    private LocalDateTime updatedDatetime;

    @Builder
    public UserCrewMappingEntity(Long userCrewMappingId, Long crewId, Long userId, LocalDateTime createdDatetime, LocalDateTime updatedDatetime) {
        this.userCrewMappingId = userCrewMappingId;
        this.crewId = crewId;
        this.userId = userId;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }
}
