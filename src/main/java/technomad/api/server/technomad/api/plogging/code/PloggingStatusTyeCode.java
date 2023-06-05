package technomad.api.server.technomad.api.plogging.code;

import lombok.Getter;
import technomad.api.server.technomad.core.code.BaseTypeCodeInterface;

@Getter
public enum PloggingStatusTyeCode implements BaseTypeCodeInterface {
    ING    ("I", "진행중"),
    FINISH  ("F", "종료");

    private final String code;
    private final String description;

    PloggingStatusTyeCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}