package technomad.api.server.technomad.api.crew.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrewFeedDetailRequestDto {
    @Schema(description = "크루 피드 고유번호", example = "1")
    private Long crewFeedId;
}
