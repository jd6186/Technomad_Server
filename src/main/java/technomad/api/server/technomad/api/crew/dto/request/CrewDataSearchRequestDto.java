package technomad.api.server.technomad.api.crew.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.core.dto.base.BasePagingRequest;

@Getter
@Setter
public class CrewDataSearchRequestDto extends BasePagingRequest {
    @Schema(description = "크루 고유번호", example = "1")
    private Long crewId;
    public CrewDataSearchRequestDto(int pageNumber, int pageSize, String orderIsDescYn, Long crewId) {
        super(pageNumber, pageSize, orderIsDescYn);
        this.crewId = crewId;
    }
}
