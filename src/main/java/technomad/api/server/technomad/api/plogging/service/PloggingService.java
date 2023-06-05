package technomad.api.server.technomad.api.plogging.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.plogging.code.GarbagePickupCapacityPercentageTypeCode;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.api.plogging.dto.entity.PloggingEntity;
import technomad.api.server.technomad.api.plogging.dto.response.ApprovalImgAnalysisResponseDto;
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

    public ApprovalEntity getApprovalById(Long approvalId){
        return approvalRepository.findById(approvalId).orElse(null);
    }

    public PloggingEntity savePlogging(PloggingEntity ploggingEntity){
        return ploggingRepository.save(ploggingEntity);
    }

    public PloggingEntity getPloggingById(Long ploggingId){
        return ploggingQuery.findByPloggingId(ploggingId);
    }

    public PloggingEntity getPloggingByApprovalId(Long approvalId){
        return ploggingQuery.findByPloggingApprovalId(approvalId);
    }

    // TODO - AI 활용해서 분석 후 아래 3개 결과값 리턴하는 빅데이터 모델 만들어야함
    public static ApprovalImgAnalysisResponseDto getApprovalImgAnalysis() {
        return ApprovalImgAnalysisResponseDto
                .builder()
                .rubbishBagLiter(25)
                .garbagePickupCapacityPercentageTypeCode(GarbagePickupCapacityPercentageTypeCode.PERCENTAGE_50.getCode())
                .garbagePickupCapacityPercentageTypeDescription(GarbagePickupCapacityPercentageTypeCode.PERCENTAGE_50.getDescription())
                .build();
    }
}
