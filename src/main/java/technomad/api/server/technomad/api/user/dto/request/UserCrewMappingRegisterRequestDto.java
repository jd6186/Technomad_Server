package technomad.api.server.technomad.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.api.user.dto.entity.UserCrewMappingEntity;
import technomad.api.server.technomad.core.util.CommonUtil;

@Getter
@Setter
public class UserCrewMappingRegisterRequestDto {
    @Schema(description = "USER 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long userId;
    @Schema(description = "CREW 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private Long crewId;

    public UserCrewMappingEntity getUserCrewMappingEntity(){
        return UserCrewMappingEntity
                .builder()
                .userId(userId)
                .crewId(crewId)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .isDelete("N")
                .build();
    }
}
