package technomad.api.server.technomad.api.crew.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.crew.query.CrewQuery;
import technomad.api.server.technomad.api.crew.repository.CrewRepository;

@Service
public class CrewService {
    private final CrewQuery crewQuery;
    private final CrewRepository crewRepository;

    public CrewService(CrewQuery crewQuery, CrewRepository crewRepository) {
        this.crewQuery = crewQuery;
        this.crewRepository = crewRepository;
    }

}
