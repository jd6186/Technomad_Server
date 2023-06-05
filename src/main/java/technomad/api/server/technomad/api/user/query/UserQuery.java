package technomad.api.server.technomad.api.user.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;
import technomad.api.server.technomad.api.user.dto.response.QUserTodayPloggingHistoryResponseDto;
import technomad.api.server.technomad.api.user.dto.response.UserTodayPloggingHistoryResponseDto;
import technomad.api.server.technomad.core.util.CommonUtil;

import static technomad.api.server.technomad.api.plogging.dto.entity.QApprovalEntity.approvalEntity;
import static technomad.api.server.technomad.api.plogging.dto.entity.QPloggingEntity.ploggingEntity;
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

    public UserTodayPloggingHistoryResponseDto findTodayUserPloggingHistory(Long userId){
        return jpaQueryFactory.select(new QUserTodayPloggingHistoryResponseDto(
                        ploggingEntity.walkingCount.sum()
                        , ploggingEntity.trashLiter.sum()
                        , userEntity.targetTrashLiter
                        , ploggingEntity.exerciseDistance.sum()
                ))
                .from(approvalEntity)
                .innerJoin(ploggingEntity)
                .on(
                        ploggingEntity.approvalId.eq(approvalEntity.approvalId)
                        , ploggingEntity.startDatetime.between(CommonUtil.nowStartLocalDateTime(), CommonUtil.nowEndLocalDateTime())
                        , ploggingEntity.isDelete.eq("N")
                )
                .innerJoin(userEntity)
                .on(approvalEntity.userId.eq(userEntity.userId))
                .where(
                        approvalEntity.userId.eq(userId)
                        , approvalEntity.isDelete.eq("N")
                )
                .fetchOne();
    }
}
