package technomad.api.server.technomad.core.dto.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @CreatedBy
    @Comment("생성자 고유번호")
    @Column(name = "CREATED_USER_ID")
    private Long createdUserId;

    @CreatedDate
    @Comment("생성일자")
    @Column(name = "CREATED_DATETIME")
    private LocalDateTime createdDatetime;

    @LastModifiedBy
    @Comment("수정자 고유번호")
    @Column(name = "MODIFIED_USER_ID")
    private Long modifiedUserId;

    @LastModifiedDate
    @Comment("수정일자")
    @Column(name = "MODIFIED_DATETIME")
    private LocalDateTime modifiedDatetime;

    public BaseEntity(Long createdUserId,
                      LocalDateTime createdDatetime,
                      Long modifiedUserId,
                      LocalDateTime modifiedDatetime) {
        this.createdUserId = createdUserId;
        this.createdDatetime = createdDatetime;
        this.modifiedUserId = modifiedUserId;
        this.modifiedDatetime = modifiedDatetime;
    }
}


