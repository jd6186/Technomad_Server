package technomad.api.server.technomad.core.error;

import lombok.extern.slf4j.Slf4j;
import technomad.api.server.technomad.core.code.ErrorCode;

@Slf4j
public class CustomException extends Exception{
    public CustomException(ErrorCode indistAdminApiErrorCode, String className){
        log.error("Exception 발생 > Code : " + indistAdminApiErrorCode.getCode() + ", \t Message : " + indistAdminApiErrorCode.getDescription() + ", \t OccurredClass : " + className);
    }
}