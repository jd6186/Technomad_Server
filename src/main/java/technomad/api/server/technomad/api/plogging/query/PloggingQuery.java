package technomad.api.server.technomad.api.plogging.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

@Component
public class PloggingQuery {
    private final JPAQueryFactory jpaQueryFactory;
    public PloggingQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
