package technomad.api.server.technomad.api.user.service;

import org.springframework.stereotype.Service;
import technomad.api.server.technomad.api.user.query.UserQuery;
import technomad.api.server.technomad.api.user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserQuery userQuery;
    public UserService(UserRepository userRepository, UserQuery userQuery) {
        this.userRepository = userRepository;
        this.userQuery = userQuery;
    }

}
