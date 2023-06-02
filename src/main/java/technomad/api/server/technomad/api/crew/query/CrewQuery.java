package technomad.api.server.technomad.api.crew.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

@Component
public class CrewQuery {
    private final JPAQueryFactory jpaQueryFactory;
    public CrewQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
