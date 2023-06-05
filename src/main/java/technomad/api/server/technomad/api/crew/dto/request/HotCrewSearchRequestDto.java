package technomad.api.server.technomad.api.crew.dto.request;

import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.core.dto.base.BasePagingRequest;

@Getter
@Setter
public class HotCrewSearchRequestDto extends BasePagingRequest {
    public HotCrewSearchRequestDto(int pageNumber, int pageSize, String orderIsDescYn) {
        super(pageNumber, pageSize, orderIsDescYn);
    }
}
