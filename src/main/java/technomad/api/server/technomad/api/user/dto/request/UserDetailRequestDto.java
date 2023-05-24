package technomad.api.server.technomad.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequestDto{
    @Schema(description = "USER 고유번호", example = "1")
    private Long userId;
}
