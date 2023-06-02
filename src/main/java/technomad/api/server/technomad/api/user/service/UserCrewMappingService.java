package technomad.api.server.technomad.api.user.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.user.dto.entity.UserCrewMappingEntity;
import technomad.api.server.technomad.api.user.query.UserCrewMappingQuery;
import technomad.api.server.technomad.api.user.repository.UserCrewMappingRepository;
import technomad.api.server.technomad.core.util.CommonUtil;

@Service
public class UserCrewMappingService {
    private final UserCrewMappingQuery userCrewMappingQuery;
    private final UserCrewMappingRepository userCrewMappingRepository;

    public UserCrewMappingService(UserCrewMappingQuery userCrewMappingQuery, UserCrewMappingRepository userCrewMappingRepository) {
        this.userCrewMappingQuery = userCrewMappingQuery;
        this.userCrewMappingRepository = userCrewMappingRepository;
    }

    public UserCrewMappingEntity save(UserCrewMappingEntity userCrewMappingEntity){
        return userCrewMappingRepository.save(userCrewMappingEntity);
    }

    public UserCrewMappingEntity registerUserCrewMapping(Long userId, Long crewId){
        UserCrewMappingEntity userCrewMappingEntity = UserCrewMappingEntity
                .builder()
                .userId(userId)
                .crewId(crewId)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .build();
        return save(userCrewMappingEntity);
    }
}
