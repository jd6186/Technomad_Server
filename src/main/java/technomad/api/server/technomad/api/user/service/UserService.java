package technomad.api.server.technomad.api.user.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;
import technomad.api.server.technomad.api.user.dto.response.UserTodayPloggingHistoryResponseDto;
import technomad.api.server.technomad.api.user.query.UserQuery;
import technomad.api.server.technomad.api.user.repository.UserRepository;
import technomad.api.server.technomad.core.util.CommonUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserQuery userQuery;
    public UserService(UserRepository userRepository, UserQuery userQuery) {
        this.userRepository = userRepository;
        this.userQuery = userQuery;
    }

    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public UserEntity registerUser(String accountId){
        UserEntity userEntity = UserEntity
                .builder()
                .accountId(accountId)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .userStatusCode("N")
                .build();
        return save(userEntity);
    }

    public UserEntity getUserEntity(String accountId){
        return userQuery.findByAccountId(accountId);
    }

    public UserTodayPloggingHistoryResponseDto getUserPloggingHistoryList(Long userId){
        return userQuery.findTodayUserPloggingHistory(userId);
    }
}
