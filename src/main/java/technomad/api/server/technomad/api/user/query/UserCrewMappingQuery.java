package technomad.api.server.technomad.api.user.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;

import java.util.List;

import static technomad.api.server.technomad.api.crew.dto.entity.QCrewEntity.crewEntity;
import static technomad.api.server.technomad.api.user.dto.entity.QUserCrewMappingEntity.userCrewMappingEntity;


@Component
public class UserCrewMappingQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public UserCrewMappingQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<CrewEntity> findUserCrewList(Long userId){
        return jpaQueryFactory.select(crewEntity)
                .from(userCrewMappingEntity)
                .innerJoin(crewEntity)
                .on(userCrewMappingEntity.crewId.eq(crewEntity.crewId))
                .where(userCrewMappingEntity.userId.eq(userId))
                .orderBy(userCrewMappingEntity.createdDatetime.desc())
                .fetch();
    }
}
