package technomad.api.server.technomad.api.common.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.common.dto.response.LoginResponseDto;
import technomad.api.server.technomad.api.common.dto.response.QLoginResponseDto;

import static technomad.api.server.technomad.api.user.dto.entity.QUserEntity.userEntity;

@Component
public class CommonQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public CommonQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public LoginResponseDto findUserLoginData(String accountId){
        return jpaQueryFactory.select(new QLoginResponseDto(
                    userEntity.userId
                    , userEntity.nickname
                    , userEntity.targetTrashLiter
                ))
                .from(userEntity)
                .where(userEntity.accountId.eq(accountId))
                .fetchOne();
    }
}
