package technomad.api.server.technomad.api.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technomad.api.server.technomad.api.crew.dto.entity.CrewEntity;
import technomad.api.server.technomad.api.crew.dto.entity.CrewFeedEntity;
import technomad.api.server.technomad.api.crew.dto.request.*;
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

    @Operation(summary = "현재 플로깅을 하고 있는 유저수 조회 API", description = "현재 플로깅을 하고 있는 유저수 조회 API")
    @GetMapping("/now-user-count")
    public ResponseEntity<TechnomadResponseDto<Integer>> nowPloggingUserCount(CrewDetailRequestDto crewDetailRequestDto){
        int response = crewService.getNowPloggingUserCount(crewDetailRequestDto.getCrewId());
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "핫한 크루목록 조회 API", description = "핫한 크루목록 조회 API")
    @GetMapping("/hot-crew-list")
    public ResponseEntity<TechnomadResponseDto<List<CrewEntity>>> getHotCrewList(HotCrewSearchRequestDto searchRequestDto) {
        List<CrewEntity> response = crewService.getHotCrewList(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }

    @Operation(summary = "크루등록 API", description = "크루등록 API")
    @PostMapping
    public ResponseEntity<TechnomadResponseDto<CrewEntity>> registerCrew(@Valid @RequestBody CrewRegisterRequestDto crewRegisterRequestDto) {
        CrewEntity crewEntity = crewRegisterRequestDto.getCrewEntity();
        CrewEntity response = crewService.saveCrew(crewEntity);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 수정 API", description = "크루 수정 API")
    @PutMapping
    public ResponseEntity<TechnomadResponseDto<CrewEntity>> updateCrew(@Valid @RequestBody CrewUpdateRequestDto crewUpdateRequestDto) {
        CrewEntity crewEntity = crewService.getCrewDetail(crewUpdateRequestDto.getCrewId());
        crewUpdateRequestDto.getUpdateCrewEntity(crewEntity);
        CrewEntity response = crewService.saveCrew(crewEntity);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 삭제 API", description = "크루 삭제 API")
    @DeleteMapping
    public ResponseEntity<TechnomadResponseDto<CrewEntity>> deleteCrew(CrewDetailRequestDto crewDetailRequestDto) {
        CrewEntity crewEntity = crewService.getCrewDetail(crewDetailRequestDto.getCrewId());
        crewEntity.setIsDelete("Y");
        CrewEntity response = crewService.saveCrew(crewEntity);
        return TechnomadResponseDto.of(response);
    }


    // 크루 피드 ------------------------------------------------------------------------

    @Operation(summary = "크루 피드 목록 조회 API", description = "크루 피드 목록 조회 API")
    @GetMapping("/feed/get-list")
    public ResponseEntity<TechnomadResponseDto<List<CrewFeedEntity>>> getCrewFeedList(CrewDataSearchRequestDto searchRequestDto) {
        List<CrewFeedEntity> response = crewService.getCrewFeedList(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 피드 등록 API", description = "크루 피드 등록 API")
    @PostMapping("/feed")
    public ResponseEntity<TechnomadResponseDto<CrewFeedEntity>> registerCrewFeed(@Valid @RequestBody CrewFeedRegisterRequestDto crewFeedRegisterRequestDto) {
        CrewFeedEntity crewFeedEntity = crewFeedRegisterRequestDto.getCrewFeedEntity();
        CrewFeedEntity response = crewService.saveCrewFeed(crewFeedEntity);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 피드 수정 API", description = "크루 피드 수정 API")
    @PutMapping("/feed")
    public ResponseEntity<TechnomadResponseDto<CrewFeedEntity>> registerCrewFeed(@Valid @RequestBody CrewFeedUpdateRequestDto crewFeedUpdateRequestDto) {
        CrewFeedEntity crewFeedEntity = crewService.getCrewFeedDetail(crewFeedUpdateRequestDto.getCrewFeedId());
        crewFeedUpdateRequestDto.getUpdateCrewFeedEntity(crewFeedEntity);
        CrewFeedEntity response = crewService.saveCrewFeed(crewFeedEntity);
        return TechnomadResponseDto.of(response);
    }
    @Operation(summary = "크루 피드 삭제 API", description = "크루 피드 삭제 API")
    @DeleteMapping("/feed")
    public ResponseEntity<TechnomadResponseDto<CrewFeedEntity>> registerCrewFeed(CrewFeedDetailRequestDto crewFeedDetailRequestDto) {
        CrewFeedEntity crewFeedEntity = crewService.getCrewFeedDetail(crewFeedDetailRequestDto.getCrewFeedId());
        crewFeedEntity.setIsDelete("Y");
        CrewFeedEntity response = crewService.saveCrewFeed(crewFeedEntity);
        return TechnomadResponseDto.of(response);
    }



    // 크루 인증 ------------------------------------------------------------------------
    @Operation(summary = "크루 인증 목록 조회 API", description = "크루 인증 목록 조회 API")
    @GetMapping("/plogging/approval-list")
    public ResponseEntity<TechnomadResponseDto<List<ApprovalEntity>>> getCrewApprovalList(CrewDataSearchRequestDto searchRequestDto) {
        List<ApprovalEntity> response = crewService.getCrewApprovalList(searchRequestDto);
        return TechnomadResponseDto.of(response);
    }
}
