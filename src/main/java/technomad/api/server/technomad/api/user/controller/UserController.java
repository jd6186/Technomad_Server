package technomad.api.server.technomad.api.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technomad.api.server.technomad.api.user.service.UserService;

@Tag(name = "User Controller", description = "User 관리 컨트롤러")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO - 나의 활동이력
    // TODO - 내가 가입한 크루 목록
}
