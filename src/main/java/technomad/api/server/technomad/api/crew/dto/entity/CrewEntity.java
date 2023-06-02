package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW")
public class CrewEntity {
    @Id
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

    @Basic
    @Column(name = "CREW_NAME", length = 100, nullable = false)
    private String crewName;

    @Basic
    @Column(name = "CREW_MAIN_IMG_FILE_ID", nullable = true)
    private Long crewMainImgFileId;

    @Basic
    @Column(name = "CREW_CONTENT", columnDefinition = "text", nullable = false)
    private String crewContent;

    @Basic
    @Column(name = "MAX_COUNT", nullable = false)
    private Integer maxCount;
}
