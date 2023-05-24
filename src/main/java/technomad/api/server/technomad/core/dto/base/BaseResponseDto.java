package technomad.api.server.technomad.core.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import technomad.api.server.technomad.core.code.ErrorCode;

@Getter
public class BaseResponseDto<T> {
    @Schema(description = "응답 데이터", example = "응답 결과 데이터")
    @NotBlank
    private final T responseData;

    @Schema(description = "에러코드(이슈 없을 시 200)", example = "에러코드(이슈 없을 시 200)")
    @NotBlank
    private final String errorCode;

    @Schema(description = "에러 발생 사유", example = "에러 발생 사유")
    @NotBlank
    private final String errorReason;

    @Builder
    public BaseResponseDto(T responseData, String errorCode, String errorReason) {
        this.responseData = responseData;
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    public static BaseResponseDto of(Object responseData){
        return BaseResponseDto.builder()
                .responseData(responseData)
                .errorCode(ErrorCode.SUCCESS.getCode())
                .errorReason(ErrorCode.SUCCESS.getDescription())
                .build();
    }

    public static BaseResponseDto of(Object responseData, ErrorCode indistAdminApiErrorCode){
        return BaseResponseDto.builder()
                .responseData(responseData)
                .errorCode(indistAdminApiErrorCode.getCode())
                .errorReason(indistAdminApiErrorCode.getDescription())
                .build();
    }
}
