package technomad.api.server.technomad.core.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagingRequest {
    private final int pageNumber;
    private final int pageSize;

    public BasePagingRequest(int pageNumber, int pageSize){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}