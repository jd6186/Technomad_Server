package technomad.api.server.technomad.api.common.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    @Schema(description = "유저 고유번호", example = "1")
    private final Long userId;
    @Schema(description = "유저 로그인 아이디", example = "user1234")
    private final String accountId;
    @Schema(description = "유저 닉네임", example = "뉴비")
    private final String nickName;
    @Schema(description = "유저 목표 쓰레기 리터수", example = "25")
    private final Integer targetLiter;

    @QueryProjection
    public LoginResponseDto(Long userId, String accountId, String nickName, Integer targetLiter) {
        this.userId = userId;
        this.accountId = accountId;
        this.nickName = nickName;
        this.targetLiter = targetLiter;
    }
}
