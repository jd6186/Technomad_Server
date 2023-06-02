package technomad.api.server.technomad.core.dto.base;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import technomad.api.server.technomad.core.code.ErrorCode;

@Getter
public class BaseSearchResponseDto<T> extends TechnomadResponseDto{
    private final Long allCount;
    private final Long searchCount;

    public BaseSearchResponseDto(T responseData, Long allCount, Long searchCount, String errorCode, String errorReason) {
        super(responseData, errorCode, errorReason);
        this.allCount = allCount;
        this.searchCount = searchCount;
    }

    public static <T> ResponseEntity<BaseSearchResponseDto<T>> of(T responseData, Long allCount, Long searchCount){
        BaseSearchResponseDto<T> response = new BaseSearchResponseDto<T>(responseData, allCount, searchCount, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription());
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<BaseSearchResponseDto<T>> of(T responseData, Long allCount, Long searchCount, ErrorCode errorCode){
        BaseSearchResponseDto<T> response = new BaseSearchResponseDto<T>(responseData, allCount, searchCount, errorCode.getCode(), errorCode.getDescription());
        return ResponseEntity.ok(response);
    }
}