package technomad.api.server.technomad.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomad.api.server.technomad.api.user.dto.entity.UserCrewMappingEntity;

@Repository
public interface UserCrewMappingRepository extends JpaRepository<UserCrewMappingEntity, Long> {
}
