package technomad.api.server.technomad.api.user.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import technomad.api.server.technomad.core.dto.entity.BaseAvailabilityEntity;

@Getter
@Entity
@Table(name = "TB_USER", schema = "TECHNOMAD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseAvailabilityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Comment("USER 고유번호")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    @Basic
    @Comment("USER 로그인 아이디")
    @Column(name = "ACCOUNT_ID", nullable = false, length = 100)
    private String accountId;
    @Basic
    @Comment("USER 로그인 해쉬 비밀번호")
    @Column(name = "PASSWORD", nullable = false, length = 1000)
    private String password;
    @Basic
    @Comment("USER명")
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @Builder
    public UserEntity(Long userId, String accountId, String password) {
        this.userId = userId;
        this.accountId = accountId;
        this.password = password;
    }
}
