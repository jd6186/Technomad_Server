package technomad.api.server.technomad.api.common.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_FILE")
public class FileEntity {
    @Id
    @Comment("파일 고유번호")
    @Column(name = "FILE_ID", nullable = false)
    private Long fileId;

    @Basic
    @Comment("유저가 작성한 파일명")
    @Column(name = "ORIGIN_NAME", nullable = false, length = 100)
    private String originName;

    @Basic
    @Comment("파일 저장 BaseURL")
    @Column(name = "BASE_URL", nullable = false, length = 2000)
    private String baseUrl;

    @Basic
    @Comment("파일 저장 경로")
    @Column(name = "FILE_PATH", nullable = false, length = 2000)
    private String filePath;

    @Basic
    @Comment("저장된 파일명")
    @Column(name = "SAVE_NAME", nullable = false, length = 100)
    private String saveName;

    @Basic
    @Comment("파일 확장자")
    @Column(name = "FILE_EXTENSION", nullable = false, length = 50)
    private String fileExtension;

    @Basic
    @Comment("파일 등록일시")
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Comment("파일 수정일시")
    @Column(name = "UPDATED_DATETIME", nullable = false)
    private LocalDateTime updatedDatetime;
}
