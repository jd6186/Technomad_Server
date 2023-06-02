package technomad.api.server.technomad.api.user.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;


@Component
public class UserQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public UserQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
