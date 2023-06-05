package technomad.api.server.technomad.api.plogging.code;

import lombok.Getter;
import technomad.api.server.technomad.core.code.BaseTypeCodeInterface;

@Getter
public enum GarbagePickupCapacityPercentageTypeCode implements BaseTypeCodeInterface {
    PERCENTAGE_0    ("01", "0%"),
    PERCENTAGE_25  ("02", "25%"),
    PERCENTAGE_50  ("03", "50%"),
    PERCENTAGE_75  ("04", "75%"),
    PERCENTAGE_100  ("05", "100%");

    private final String code;
    private final String description;

    GarbagePickupCapacityPercentageTypeCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}