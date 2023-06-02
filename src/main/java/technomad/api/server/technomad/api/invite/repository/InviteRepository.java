package technomad.api.server.technomad.api.invite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomad.api.server.technomad.api.invite.dto.entity.InviteEntity;

@Repository
public interface InviteRepository extends JpaRepository<InviteEntity, Long> {
}
