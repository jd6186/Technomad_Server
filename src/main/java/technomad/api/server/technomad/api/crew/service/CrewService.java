package technomad.api.server.technomad.api.crew.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.api.crew.dto.request.CrewDataSearchRequestDto;
import technomad.api.server.technomad.api.crew.dto.request.HotCrewSearchRequestDto;
import technomad.api.server.technomad.api.crew.query.CrewQuery;
import technomad.api.server.technomad.api.crew.repository.CrewRepository;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;

import java.util.List;

@Service
public class CrewService {
    private final CrewQuery crewQuery;
    private final CrewRepository crewRepository;

    public CrewService(CrewQuery crewQuery, CrewRepository crewRepository) {
        this.crewQuery = crewQuery;
        this.crewRepository = crewRepository;
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

    public List<ApprovalEntity> getCrewApprovalList(CrewDataSearchRequestDto searchRequestDto){
        return crewQuery.findCrewApprovalList(searchRequestDto);

    }
}
