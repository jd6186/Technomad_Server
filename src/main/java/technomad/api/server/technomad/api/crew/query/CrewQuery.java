package technomad.api.server.technomad.api.crew.query;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.api.crew.dto.request.CrewDataSearchRequestDto;
import technomad.api.server.technomad.api.crew.dto.request.HotCrewSearchRequestDto;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.core.util.QueryDslUtil;

import java.time.LocalDateTime;
import java.util.List;

import static technomad.api.server.technomad.api.crew.dto.entity.QCrewEntity.crewEntity;
import static technomad.api.server.technomad.api.crew.dto.entity.QCrewFeedEntity.crewFeedEntity;
import static technomad.api.server.technomad.api.plogging.dto.entity.QApprovalEntity.approvalEntity;
import static technomad.api.server.technomad.api.plogging.dto.entity.QPloggingEntity.ploggingEntity;

@Component
public class CrewQuery {
    private final JPAQueryFactory jpaQueryFactory;
    public CrewQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public CrewEntity findCrewDetail(Long crewId){
        return jpaQueryFactory.select(crewEntity)
                .from(crewEntity)
                .where(crewEntity.crewId.eq(crewId), crewEntity.isDelete.eq("N"))
                .fetchOne();
    }
    public List<CrewEntity> findHotCrewList(HotCrewSearchRequestDto searchRequestDto){
        OrderSpecifier<LocalDateTime> order = (searchRequestDto.getOrderIsDescYn().equals("N")) ? crewEntity.createdDatetime.asc() : crewEntity.createdDatetime.desc();
        return jpaQueryFactory.select(crewEntity)
                .from(crewEntity)
                .where(crewEntity.isHotCrew.eq("Y"), crewEntity.isDelete.eq("N"))
                .offset(QueryDslUtil.offsetCount(searchRequestDto.getPageNumber(), searchRequestDto.getPageSize()))
                .limit(searchRequestDto.getPageSize())
                .orderBy(order)
                .fetch();
    }

    public List<CrewFeedEntity> findCrewFeedList(CrewDataSearchRequestDto searchRequestDto){
        OrderSpecifier<LocalDateTime> order = (searchRequestDto.getOrderIsDescYn().equals("N")) ? crewEntity.createdDatetime.asc() : crewEntity.createdDatetime.desc();
        return jpaQueryFactory.select(crewFeedEntity)
                .from(crewFeedEntity)
                .where(crewFeedEntity.crewId.eq(searchRequestDto.getCrewId()), crewFeedEntity.isDelete.eq("N"))
                .offset(QueryDslUtil.offsetCount(searchRequestDto.getPageNumber(), searchRequestDto.getPageSize()))
                .limit(searchRequestDto.getPageSize())
                .orderBy(order)
                .fetch();
    }

    public CrewFeedEntity findCrewFeedDetail(Long crewFeedId){
        return jpaQueryFactory.select(crewFeedEntity)
                .from(crewFeedEntity)
                .where(crewFeedEntity.crewFeedId.eq(crewFeedId), crewFeedEntity.isDelete.eq("N"))
                .fetchOne();
    }

    public List<ApprovalEntity> findCrewApprovalList(CrewDataSearchRequestDto searchRequestDto){
        OrderSpecifier<LocalDateTime> order = (searchRequestDto.getOrderIsDescYn().equals("N")) ? crewEntity.createdDatetime.asc() : crewEntity.createdDatetime.desc();
        return jpaQueryFactory.select(approvalEntity)
                .from(approvalEntity)
                .innerJoin(ploggingEntity)
                .on(approvalEntity.approvalId.eq(ploggingEntity.approvalId))
                .where(ploggingEntity.crewId.eq(searchRequestDto.getCrewId()))
                .offset(QueryDslUtil.offsetCount(searchRequestDto.getPageNumber(), searchRequestDto.getPageSize()))
                .limit(searchRequestDto.getPageSize())
                .orderBy(order)
                .fetch();
    }
}
