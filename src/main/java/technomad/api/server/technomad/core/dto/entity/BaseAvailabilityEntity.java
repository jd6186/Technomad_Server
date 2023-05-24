package technomad.api.server.technomad.core.dto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAvailabilityEntity extends BaseEntity  implements Serializable {
    @Basic
    @Comment("사용여부")
    @ColumnDefault("'Y'")
    @Column(name = "IS_USE_YN", nullable = false, length = 1)
    private String isUseYn;
    @Basic
    @Comment("삭제여부")
    @ColumnDefault("'N'")
    @Column(name = "IS_DELETE_YN", nullable = false, length = 1)
    private String isDeleteYn;

    public BaseAvailabilityEntity(Long createdUserId,
                                  LocalDateTime createdDatetime,
                                  Long modifiedUserId,
                                  LocalDateTime modifiedDatetime) {
        super(createdUserId, createdDatetime, modifiedUserId, modifiedDatetime);
    }

    @PrePersist
    public void prePersist() {
        this.isDeleteYn = this.isDeleteYn == null ? "N" : this.isDeleteYn;
        this.isUseYn = this.isUseYn == null ? "Y" : this.isUseYn;
    }
}
