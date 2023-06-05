package technomad.api.server.technomad.api.plogging.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PloggingDetailRequestDto {
    @Schema(description = "플로깅 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long ploggingId;
}
