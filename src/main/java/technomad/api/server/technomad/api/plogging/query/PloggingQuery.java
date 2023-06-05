package technomad.api.server.technomad.api.plogging.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;

import static technomad.api.server.technomad.api.plogging.dto.entity.QPloggingEntity.ploggingEntity;

@Component
public class PloggingQuery {
    private final JPAQueryFactory jpaQueryFactory;
    public PloggingQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public PloggingEntity findByPloggingId(Long ploggingId){
        return jpaQueryFactory.select(ploggingEntity)
                .from(ploggingEntity)
                .where(ploggingEntity.ploggingId.eq(ploggingId)
                        , ploggingEntity.isDelete.eq("N"))
                .fetchOne();
    }

    public PloggingEntity findByPloggingApprovalId(Long approvalId){
        return jpaQueryFactory.select(ploggingEntity)
                .from(ploggingEntity)
                .where(ploggingEntity.approvalId.eq(approvalId))
                .fetchOne();
    }
}
