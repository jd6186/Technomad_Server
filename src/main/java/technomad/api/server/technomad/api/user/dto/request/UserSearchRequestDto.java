package technomad.api.server.technomad.api.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import technomad.api.server.technomad.core.dto.base.BasePagingRequest;

import java.time.LocalDate;

@Getter
@Setter
public class UserSearchRequestDto extends BasePagingRequest {
    @Schema(description = "USER 고유번호", example = "1")
    private Long userId;

    @Schema(description = "USER 로그인 ID", example = "user1234")
    private String accountId;

    @Schema(description = "검색 시작일", example = "2023-01-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Schema(description = "검색 종료일", example = "2023-12-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Builder
    public UserSearchRequestDto(int pageNumber, int pageSize, String orderIsDescYn, Long userId, String accountId, LocalDate startDate, LocalDate endDate) {
        super(pageNumber, pageSize, orderIsDescYn);
        this.userId = userId;
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
