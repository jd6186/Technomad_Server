package technomad.api.server.technomad.api.user.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;


@Component
public class UserCrewMappingQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public UserCrewMappingQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


}
