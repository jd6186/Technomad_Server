package technomad.api.server.technomad.api.user.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.user.dto.entity.UserEntity;
import technomad.api.server.technomad.api.user.dto.request.UserSearchRequestDto;
import technomad.api.server.technomad.api.user.query.UserQuery;
import technomad.api.server.technomad.api.user.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserQuery userQuery;
    public UserService(UserRepository userRepository, UserQuery userQuery) {
        this.userRepository = userRepository;
        this.userQuery = userQuery;
    }

    public UserEntity getUserDetailById(Long userId){
        return userQuery.findById(userId);
    }
    public UserEntity getUserDetailByAccountId(String accountId){
        return userQuery.findByAccountId(accountId);
    }

    public List<UserEntity> getSearchUser(UserSearchRequestDto searchRequestDto){
        return userQuery.findAllUserBySearchRequestDto(searchRequestDto);
    }
}
