package technomad.api.server.technomad.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCrewMappingDetailRequestDto {
    @Schema(description = "USER 크루 가입 이력 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long userCrewMappingId;
}
