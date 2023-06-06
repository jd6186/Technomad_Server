package technomad.api.server.technomad.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CommonUtil {

    public static String dtoToJson(Object dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dto);
    }

    public static String getUploadFileName(String originalName){
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + "_" + originalName;
    }

    public static String getNowDatetimeString(){
        return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static Timestamp dateFormatChange(String date){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime dateTime = LocalDate.parse(date, formatter).atStartOfDay();
        return Timestamp.valueOf(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public static LocalDateTime nowLocalDateTime(){
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        return LocalDateTime.now(seoulZoneId);
    }

    public static LocalDateTime nowStartLocalDateTime(){
        LocalDateTime now = nowLocalDateTime();
        return now.with(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0));
    }

    public static LocalDateTime nowEndLocalDateTime(){
        LocalDateTime now = nowLocalDateTime();
        return now.with(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59));
    }

    public static boolean isNumeric(String s) {
        if (s == null) return false;
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Double typeDoubleNullCheck(String doubleValue){
        return isNumeric(doubleValue) ? Double.parseDouble(doubleValue) : 0;
    }

    public static Object typeJSONObjectNullCheck(JSONObject jsonObject, String elementName){
        return Optional.ofNullable(jsonObject.get(elementName)).isEmpty() ? null : jsonObject.get(elementName);
    }

    public static Boolean isStringEmpty(String value){
        return (value == null || value.trim().equals("") || value.trim().equals("null"));
    }

    public static <T> Object dtoMapperAtoB(T A, T B){
        if(A==null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper.convertValue(A, B.getClass());
    }
}