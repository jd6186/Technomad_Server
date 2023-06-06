package technomad.api.server.technomad.core.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import technomad.api.server.technomad.core.code.ErrorCode;

@Getter
public class TechnomadResponseDto<T> {
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
    public TechnomadResponseDto(T responseData, String errorCode, String errorReason) {
        this.responseData = responseData;
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    public static <T> ResponseEntity<TechnomadResponseDto<T>> of(T responseData){
        TechnomadResponseDto response =  TechnomadResponseDto.builder()
                .responseData(responseData)
                .errorCode(ErrorCode.SUCCESS.getCode())
                .errorReason(ErrorCode.SUCCESS.getDescription())
                .build();
        return ResponseEntity.ok(response);

    }

    public static <T> ResponseEntity<TechnomadResponseDto<T>> of(Resource resource, HttpHeaders headers){
        return new ResponseEntity(resource, headers, HttpStatus.OK);

    }

    public static <T> ResponseEntity<TechnomadResponseDto<T>> of(T responseData, ErrorCode indistAdminApiErrorCode){
        TechnomadResponseDto response = TechnomadResponseDto.builder()
                .responseData(responseData)
                .errorCode(indistAdminApiErrorCode.getCode())
                .errorReason(indistAdminApiErrorCode.getDescription())
                .build();
        return ResponseEntity.ok(response);
    }
}
