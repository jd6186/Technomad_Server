package technomad.api.server.technomad.core.util;

import com.mysql.cj.util.StringUtils;
import com.querydsl.core.types.dsl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class QueryDslUtil {
    public static BooleanExpression strLikeValidCheck(StringPath column, String value){
        return (StringUtils.isEmptyOrWhitespaceOnly(value) || value.equals("0")) ? null : column.like("%" + value + "%");
    }

    public static BooleanExpression strEqCheck(StringPath column, String value){
        return (StringUtils.isEmptyOrWhitespaceOnly(value)) ? null : column.eq(value);
    }

    public static BooleanExpression longEqCheck(NumberPath<Long> column, Long value){
        return (value == null) ? null : column.eq(value);
    }

    public static BooleanExpression longLikeValidCheck(NumberPath<Long> column, Long value){
        return (value == null) ? null : column.like("%" + value + "%");
    }

    public static BooleanExpression localDateValidCheck(DatePath<LocalDate> column, LocalDate value){
        return (value == null) ? null : column.eq(value);
    }

    // 특정일에 대한 Date 날짜 범위 비교
    public static BooleanExpression localDatetimeCheckByLocalDate(DateTimePath<LocalDateTime> column, LocalDate startDate, LocalDate endDate){
        if(column == null || startDate == null || endDate == null) return null;
        BooleanExpression isGoeStartDate = column.goe(LocalDateTime.of(startDate, LocalTime.MIN));
        BooleanExpression isLoeEndDate = column.loe(LocalDateTime.of(endDate, LocalTime.MAX).withNano(0));

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
    }

    // TODO - 이건 테스트 필요
    // 특정 기간에 대한 Date 날짜 범위 비교
    public static BooleanExpression localDatetimeRangeCheckByLocalDate(DateTimePath<LocalDateTime> startColumn, DateTimePath<java.time.LocalDateTime> endColumn, LocalDate startDate, LocalDate endDate){
        if(startColumn == null || endColumn == null || startDate == null || endDate == null) return null;
        BooleanExpression isStartColumnGoeStartDate = startColumn.goe(LocalDateTime.of(startDate, LocalTime.MIN));
        BooleanExpression isStartColumnLoeEndDate = startColumn.loe(LocalDateTime.of(endDate, LocalTime.MAX).withNano(0));
        BooleanExpression isEndColumnGoeStartDate = startColumn.goe(LocalDateTime.of(startDate, LocalTime.MIN));
        BooleanExpression isEndColumnLoeEndDate = startColumn.loe(LocalDateTime.of(endDate, LocalTime.MAX).withNano(0));

        return Expressions.allOf(isStartColumnGoeStartDate, isStartColumnLoeEndDate, isEndColumnGoeStartDate, isEndColumnLoeEndDate);
    }

    // ########################### 날짜 비교 ###########################
    // Date 날짜 범위 비교
    public static BooleanExpression localDateRangeCheck(DateTimePath<LocalDateTime> column, LocalDate startDatetime, LocalDate endDatetime){
        if(column == null || startDatetime == null || endDatetime == null) return null;
        BooleanExpression isGoeStartDate = column.goe(startDatetime.atStartOfDay());
        BooleanExpression isLoeEndDate = column.loe(endDatetime.atTime(LocalTime.MAX));

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
    }

    // DateTime 날짜 범위 비교
    public static BooleanExpression localDateTimeRangeCheck(DateTimePath<LocalDateTime> column, LocalDateTime startDatetime, LocalDateTime endDatetime){
        if(column == null || startDatetime == null || endDatetime == null) return null;
        BooleanExpression isGoeStartDate = column.goe(startDatetime);
        BooleanExpression isLoeEndDate = column.loe(endDatetime);

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
    }
}