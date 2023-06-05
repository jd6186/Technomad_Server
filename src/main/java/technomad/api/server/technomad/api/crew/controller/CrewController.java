package technomad.api.server.technomad.api.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.api.crew.dto.request.CrewDataSearchRequestDto;
import technomad.api.server.technomad.api.crew.dto.request.CrewDetailRequestDto;
import technomad.api.server.technomad.api.crew.dto.request.HotCrewSearchRequestDto;
import technomad.api.server.technomad.api.crew.service.CrewService;
import technomad.api.server.technomad.api.plogging.dto.entity.ApprovalEntity;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

import java.util.List;

@Tag(name = "Crew Controller", description = "Crew 관리 컨트롤러")
@RestController
@RequestMapping("/crew")
public class CrewController {
    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @Operation(summary = "크루 상세 정보 조회 API", description = "크루 상세 정보 조회 API")
    @GetMapping
    public ResponseEntity<TechnomadResponseDto<CrewEntity>> getCrewDetail(CrewDetailRequestDto crewDetailRequestDto) {
        CrewEntity response = crewService.getCrewDetail(crewDetailRequestDto.getCrewId());
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "핫한 크루목록 조회 API", description = "핫한 크루목록 조회 API")
    @GetMapping("/hot-crew-list")
    public ResponseEntity<TechnomadResponseDto<List<CrewEntity>>> getHotCrewList(HotCrewSearchRequestDto searchRequestDto) {
        List<CrewEntity> response = crewService.getHotCrewList(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 피드 목록 조회 API", description = "크루 피드 목록 조회 API")
    @GetMapping("/feed-list")
    public ResponseEntity<TechnomadResponseDto<List<CrewFeedEntity>>> getCrewFeedList(CrewDataSearchRequestDto searchRequestDto) {
        List<CrewFeedEntity> response = crewService.getCrewFeedList(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 인증 목록 조회 API", description = "크루 인증 목록 조회 API")
    @GetMapping("/my-plogging-history")
    public ResponseEntity<TechnomadResponseDto<List<ApprovalEntity>>> getCrewApprovalList(CrewDataSearchRequestDto searchRequestDto) {
        List<ApprovalEntity> response = crewService.getCrewApprovalList(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }


    // 크루등록
    // 크루수정
    // 크루삭제
}
