package technomad.api.server.technomad.api.plogging.service;

import technomad.api.server.technomad.api.plogging.query.PloggingQuery;
import technomad.api.server.technomad.api.plogging.repository.PloggingRepository;

public class PloggingService {
    private final PloggingQuery ploggingQuery;
    private final PloggingRepository ploggingRepository;

    public PloggingService(PloggingQuery ploggingQuery, PloggingRepository ploggingRepository) {
        this.ploggingQuery = ploggingQuery;
        this.ploggingRepository = ploggingRepository;
    }
}
