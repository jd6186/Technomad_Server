package technomad.api.server.technomad.api.user.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.user.dto.entity.UserCrewMappingEntity;
import technomad.api.server.technomad.api.user.query.UserCrewMappingQuery;
import technomad.api.server.technomad.api.user.repository.UserCrewMappingRepository;
import technomad.api.server.technomad.core.util.CommonUtil;

import java.util.List;

@Service
public class UserCrewMappingService {
    private final UserCrewMappingQuery userCrewMappingQuery;
    private final UserCrewMappingRepository userCrewMappingRepository;

    public UserCrewMappingService(UserCrewMappingQuery userCrewMappingQuery, UserCrewMappingRepository userCrewMappingRepository) {
        this.userCrewMappingQuery = userCrewMappingQuery;
        this.userCrewMappingRepository = userCrewMappingRepository;
    }

    public void save(UserCrewMappingEntity userCrewMappingEntity){
        userCrewMappingRepository.save(userCrewMappingEntity);
    }

    public void registerUserCrewMapping(Long userId, Long crewId){
        UserCrewMappingEntity userCrewMappingEntity = UserCrewMappingEntity
                .builder()
                .userId(userId)
                .crewId(crewId)
                .createdDatetime(CommonUtil.nowLocalDateTime())
                .updatedDatetime(CommonUtil.nowLocalDateTime())
                .build();
        save(userCrewMappingEntity);
    }

    public List<CrewEntity> getUserCrewList(Long userId){
        return userCrewMappingQuery.findUserCrewList(userId);
    }
}
