package technomad.api.server.technomad.core.dto.base;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseSearchResponseDto<T> {
    private final Long allCount;
    private final Long searchCount;
    private final T responseData;

    @Builder
    public BaseSearchResponseDto(Long allCount, Long searchCount, T responseData) {
        this.allCount = allCount;
        this.searchCount = searchCount;
        this.responseData = responseData;
    }
}