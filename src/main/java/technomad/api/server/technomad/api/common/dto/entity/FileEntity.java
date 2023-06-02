package technomad.api.server.technomad.api.common.dto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_FILE")
public class FileEntity {
    @Id
    @Column(name = "FILE_ID", nullable = false)
    private Long fileId;

    @Basic
    @Column(name = "ORIGIN_NAME", nullable = false, length = 100)
    private String originName;

    @Basic
    @Column(name = "BASE_URL", nullable = false, length = 2000)
    private String baseUrl;

    @Basic
    @Column(name = "FILE_PATH", nullable = false, length = 2000)
    private String filePath;

    @Basic
    @Column(name = "SAVE_NAME", nullable = false, length = 100)
    private String saveName;

    @Basic
    @Column(name = "FILE_EXTENSION", nullable = false, length = 50)
    private String fileExtension;

    @Basic
    @Column(name = "CREATED_DATETIME", nullable = false)
    private LocalDateTime createdDatetime;

    @Basic
    @Column(name = "UPDATED_DATETIME", nullable = false)
    private LocalDateTime updatedDatetime;
}
