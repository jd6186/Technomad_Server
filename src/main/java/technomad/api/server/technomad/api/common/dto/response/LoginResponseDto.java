package technomad.api.server.technomad.api.common.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final String nickName;
    private final String targetLiter;

    @QueryProjection
    public LoginResponseDto(String nickName, String targetLiter) {
        this.nickName = nickName;
        this.targetLiter = targetLiter;
    }
}
