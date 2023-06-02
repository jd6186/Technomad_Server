package technomad.api.server.technomad.api.invite.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

@Component
public class InviteQuery {
    private final JPAQueryFactory jpaQueryFactory;
    public InviteQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
