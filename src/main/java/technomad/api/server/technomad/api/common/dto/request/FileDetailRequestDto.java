package technomad.api.server.technomad.api.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDetailRequestDto {
    @Schema(description = "파일 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long fileId;
}
