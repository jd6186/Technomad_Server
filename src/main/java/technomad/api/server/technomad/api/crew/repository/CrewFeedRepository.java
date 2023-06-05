package technomad.api.server.technomad.api.crew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;

@Repository
public interface CrewFeedRepository extends JpaRepository<CrewFeedEntity, Long> {
}
