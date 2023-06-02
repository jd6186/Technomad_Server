package technomad.api.server.technomad.api.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @Schema(description = "로그인 계정", example = "user1234", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String accountId;
    @Schema(description = "가입 권유받은 크루 고유번호 - 초대받지 않았으면 없음", example = "1")
    private Long crewId;
}
