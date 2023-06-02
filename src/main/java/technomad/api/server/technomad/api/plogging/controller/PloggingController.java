package technomad.api.server.technomad.api.plogging.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Plogging Controller", description = "Plogging 관리 컨트롤러")
@RestController
@RequestMapping("/plogging")
public class PloggingController {
}
