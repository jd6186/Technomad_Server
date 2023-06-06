package technomad.api.server.technomad.api.crew.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_CREW_HASH_TAG")
public class CrewHashTagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Comment("크루 고유번호")
    @Column(name = "CREW_ID")
    private Long crewId;

    @Basic
    @Comment("해쉬태그")
    @Column(name = "HASH_TAG_NAME")
    private String hashTagName;
}
