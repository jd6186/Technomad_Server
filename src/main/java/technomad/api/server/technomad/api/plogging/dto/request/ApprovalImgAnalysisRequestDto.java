package technomad.api.server.technomad.api.plogging.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalImgAnalysisRequestDto {
    @Schema(description = "인증 이미지 파일 고유번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long approvalImgFileId;
}
