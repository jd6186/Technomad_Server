package technomad.api.server.technomad.api.user.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Component;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;
import technomad.api.server.technomad.api.user.dto.request.UserSearchRequestDto;
import technomad.api.server.technomad.core.util.QueryDslUtil;

import java.util.List;

import static technomad.api.server.technomad.api.user.dto.entity.QUserEntity.userEntity;


@Component
public class UserQuery {
    private final JPAQueryFactory jpaQueryFactory;

    public UserQuery(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public UserEntity findById(Long userId){
        return jpaQueryFactory.select(userEntity)
                .from(userEntity)
                .where(userEntity.userId.eq(userId), userEntity.isDeleteYn.eq("N"))
                .fetchOne();
    }

    public UserEntity findByAccountId(String accountId){
        return jpaQueryFactory.select(userEntity)
                .from(userEntity)
                .where(userEntity.accountId.eq(accountId), userEntity.isDeleteYn.eq("N"))
                .fetchOne();
    }

    public List<UserEntity> findAllUserBySearchRequestDto(UserSearchRequestDto searchRequestDto){
        return jpaQueryFactory.select(userEntity)
                .from(userEntity)
                .where(
                        QueryDslUtil.strLikeValidCheck(userEntity.accountId, searchRequestDto.getAccountId())
                        , QueryDslUtil.longLikeValidCheck(userEntity.userId, searchRequestDto.getUserId())
                        , QueryDslUtil.strLikeValidCheck(userEntity.name, searchRequestDto.getName())
                        , QueryDslUtil.localDateRangeCheck(userEntity.createdDatetime, searchRequestDto.getStartDate(), searchRequestDto.getEndDate())
                        , userEntity.isDeleteYn.eq("N")
                )
                .offset(searchRequestDto.getPageNumber() * searchRequestDto.getPageSize())
                .limit(searchRequestDto.getPageSize())
                .fetch();
    }
}
