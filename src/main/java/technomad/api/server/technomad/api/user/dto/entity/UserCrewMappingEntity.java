package technomad.api.server.technomad.api.user.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_USER_CREW_MAPPING")
public class UserCrewMappingEntity {
    @Id
    @Column(name = "USER_CREW_MAPPING_ID", nullable = false)
    private Long userCrewMappingId;

    @Basic
    @Column(name = "CREW_ID", nullable = false)
    private Long crewId;

    @Basic
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
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
