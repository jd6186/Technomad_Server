package technomad.api.server.technomad.core.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePagingRequest {
    @Schema(description = "현재페이지번호", example = "1")
    private final int pageNumber;
    @Schema(description = "페이지 Row Size", example = "20")
    private final int pageSize;
    @Schema(description = "역순정렬(Y/N)", example = "Y")
    private final String orderIsDescYn;

    public BasePagingRequest(int pageNumber, int pageSize, String orderIsDescYn){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.orderIsDescYn = orderIsDescYn;
    }
}