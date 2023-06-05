package technomad.api.server.technomad.api.user.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class UserTodayPloggingResponseDto {
    private final Integer todayAllWalkingCount;
    private final Integer todayAllTrashLiter;
    private final Integer targetTrashLiter;
    private final Integer todayAllExerciseDistance;

    @QueryProjection
    public UserTodayPloggingResponseDto(Integer todayAllWalkingCount, Integer todayAllTrashLiter, Integer targetTrashLiter, Integer todayAllExerciseDistance) {
        this.todayAllWalkingCount = todayAllWalkingCount;
        this.todayAllTrashLiter = todayAllTrashLiter;
        this.targetTrashLiter = targetTrashLiter;
        this.todayAllExerciseDistance = todayAllExerciseDistance;
    }
}
