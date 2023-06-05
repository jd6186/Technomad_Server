package technomad.api.server.technomad.api.plogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
}
