package technomad.api.server.technomad.api.common.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.common.dto.response.LoginResponseDto;

@Component
public class CommonQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public CommonQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public LoginResponseDto findUserLoginData(){
        return null;
//        return jpaQueryFactory.select(new QLoginResponseDto(
//                    userEntity.nickname
//                    , userEntity.targetTrashLiter
//                ))
//                .from(userEntity)
//                .where(userEntity.isDeleteYn.eq("N"))
//                .fetchOne();
    }
}
