package technomad.api.server.technomad.api.common.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long userId;
    private final String nickName;
    private final Integer targetLiter;

    @QueryProjection
    public LoginResponseDto(Long userId, String nickName, Integer targetLiter) {
        this.userId = userId;
        this.nickName = nickName;
        this.targetLiter = targetLiter;
    }
}
