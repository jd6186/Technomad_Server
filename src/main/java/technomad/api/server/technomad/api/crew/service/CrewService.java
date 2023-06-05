package technomad.api.server.technomad.api.crew.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.api.crew.dto.request.CrewDataSearchRequestDto;
import technomad.api.server.technomad.api.crew.dto.request.HotCrewSearchRequestDto;
import technomad.api.server.technomad.api.crew.query.CrewQuery;
import technomad.api.server.technomad.api.crew.repository.CrewFeedRepository;
import technomad.api.server.technomad.api.crew.repository.CrewRepository;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;

import java.util.List;

@Service
public class CrewService {
    private final CrewQuery crewQuery;
    private final CrewRepository crewRepository;
    private final CrewFeedRepository crewFeedRepository;

    public CrewService(CrewQuery crewQuery, CrewRepository crewRepository, CrewFeedRepository crewFeedRepository) {
        this.crewQuery = crewQuery;
        this.crewRepository = crewRepository;
        this.crewFeedRepository = crewFeedRepository;
    }

    public CrewEntity saveCrew(CrewEntity crewEntity){
        return crewRepository.save(crewEntity);
    }

    public CrewFeedEntity saveCrewFeed(CrewFeedEntity crewFeedEntity){
        return crewFeedRepository.save(crewFeedEntity);
    }

    public CrewEntity getCrewDetail(Long crewId){
        return crewQuery.findCrewDetail(crewId);
    }

    public List<CrewEntity> getHotCrewList(HotCrewSearchRequestDto searchRequestDto){
        return crewQuery.findHotCrewList(searchRequestDto);
    }

    public List<CrewFeedEntity> getCrewFeedList(CrewDataSearchRequestDto searchRequestDto){
        return crewQuery.findCrewFeedList(searchRequestDto);
    }

    public CrewFeedEntity getCrewFeedDetail(Long crewId){
        return crewQuery.findCrewFeedDetail(crewId);
    }

    public List<ApprovalEntity> getCrewApprovalList(CrewDataSearchRequestDto searchRequestDto){
        return crewQuery.findCrewApprovalList(searchRequestDto);

    }
}
