package technomad.api.server.technomad.core.code;

import lombok.Getter;

@Getter
public enum ErrorCode implements BaseTypeCodeInterface{

    SUCCESS                     ("2000", "OK"),
    SYSTEM_ERROR                ("1001", "시스템 오류"),
    SYSTEM_PERMISSION_ERROR     ("1002", "시스템 권한 오류"),
    SYSTEM_STATUS_ERROR         ("1003", "시스템 상태 이상"),
    BAD_REQUEST_ERROR           ("9000", "부적절한 요청 오류"),
    UNAUTHORIZED_ERROR          ("9001", "인증 오류"),
    UNKNOWN_ERROR               ("9999", "알 수 없는 오류"),
    TOKEN_EXPIRATION            ("401", "접근 권한이 없습니다.");

    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}