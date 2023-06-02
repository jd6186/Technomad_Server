package technomad.api.server.technomad.api.invite.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.invite.query.InviteQuery;
import technomad.api.server.technomad.api.invite.repository.InviteRepository;

@Service
public class InviteService {
    private final InviteQuery inviteQuery;
    private final InviteRepository inviteRepository;

    public InviteService(InviteQuery inviteQuery, InviteRepository inviteRepository) {
        this.inviteQuery = inviteQuery;
        this.inviteRepository = inviteRepository;
    }
}
