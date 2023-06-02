package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW_HASH_TAG")
public class CrewHashTagEntity {
    @Id
    @Column(name = "CREW_ID")
    private Long crewId;

    @Basic
    @Column(name = "HASH_TAG_NAME")
    private String hashTagName;
}
