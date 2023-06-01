package technomad.api.server.technomad.api.common.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public CommonQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
