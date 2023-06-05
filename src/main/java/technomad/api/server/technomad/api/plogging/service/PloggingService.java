package technomad.api.server.technomad.api.plogging.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.api.plogging.query.PloggingQuery;
import technomad.api.server.technomad.api.plogging.repository.ApprovalRepository;
import technomad.api.server.technomad.api.plogging.repository.PloggingRepository;

@Service
public class PloggingService {
    private final PloggingQuery ploggingQuery;
    private final PloggingRepository ploggingRepository;
    private final ApprovalRepository approvalRepository;

    public PloggingService(PloggingQuery ploggingQuery, PloggingRepository ploggingRepository, ApprovalRepository approvalRepository) {
        this.ploggingQuery = ploggingQuery;
        this.ploggingRepository = ploggingRepository;
        this.approvalRepository = approvalRepository;
    }

    public ApprovalEntity saveApproval(ApprovalEntity approvalEntity){
        return approvalRepository.save(approvalEntity);
    }

    public PloggingEntity savePlogging(PloggingEntity ploggingEntity){
        return ploggingRepository.save(ploggingEntity);
    }

    public PloggingEntity getPloggingById(Long ploggingId){
        return ploggingQuery.findByPloggingId(ploggingId);
    }
}
