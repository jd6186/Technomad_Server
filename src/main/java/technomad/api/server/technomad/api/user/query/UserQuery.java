package technomad.api.server.technomad.api.user.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;

import static technomad.api.server.technomad.api.user.dto.entity.QUserEntity.userEntity;


@Component
public class UserQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public UserQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public UserEntity findByAccountId(String accountId){
        return jpaQueryFactory.select(userEntity)
                .from(userEntity)
                .where(userEntity.accountId.eq(accountId))
                .fetchOne();
    }
}
