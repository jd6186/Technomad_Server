package technomad.api.server.technomad.core.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ErrorCode {

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

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }

    public static String getDescription(String code) {
        ErrorCode[] typeCodeList = ErrorCode.values();
        for(ErrorCode typeCode : typeCodeList){
            if(typeCode.getCode().equals(code)){
                return typeCode.getDescription();
            }
        }
        return null;
    }

    public static List<Map<String, String>> getCodeList(){
        ErrorCode[] typeCodeList = ErrorCode.values();
        List<Map<String, String>> codeList = new ArrayList<>();
        for(ErrorCode typeCode : typeCodeList){
            Map<String, String> code = new ConcurrentHashMap<>();
            code.put("code", typeCode.getCode());
            code.put("description", typeCode.getDescription());
            codeList.add(code);
        }
        return codeList;
    }
}