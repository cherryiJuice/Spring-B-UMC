package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.MemberConverter;
import com.example.umc_practice.converter.MemberMissionConverter;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.service.memberMissionService.MemberMissionCommandService;
import com.example.umc_practice.service.memberMissionService.MemberMissionQueryService;
import com.example.umc_practice.web.dto.MemberMissionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/missions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @Operation(summary = "사용자의 미션 도전 API", description = "사용자가 미션을 도전하기 위한 API 입니다.")
    @PostMapping("/")
    public ApiResponse<?> addMemberMission(@Valid @RequestBody MemberMissionRequestDTO.CreateMemberMission requestDTO) {
        MemberMission memberMission = memberMissionCommandService.addMemberMission(requestDTO);
        return ApiResponse.onSuccess(MemberMissionConverter.toAddMissionResultDTO(memberMission));
    }

    @Operation(summary = "사용자의 미션 완료 API", description = "사용자의 미션을 완료하기 위한 API 입니다.")
    @Parameters(
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다.")
    )
    @PostMapping("/{missionId}")
    public ApiResponse<?> completeMission(@PathVariable Long missionId) {
        MemberMission memberMission = memberMissionCommandService.completeMission(missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toCompleteMissionResultDTO(memberMission));
    }

    @Operation(summary = "사용자의 미션을 조회하는 API", description = "사용자의 미션을 조회하는 API 이며 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "status", description = "0이면 도전중 1이면 완료된 미션, query string 입니다."),
            @Parameter(name = "page", description = "조회할 페이지 번호, query string 입니다.")}
    )
    @GetMapping("/")
    public ApiResponse<?> findMission(@RequestParam String status, @RequestParam Integer page) {
        Page<MemberMission> challengingMemberMission = memberMissionQueryService.getChallengingMemberMission(1L, status, page-1);
        return ApiResponse.onSuccess(MemberConverter.missionPreViewListDTO(challengingMemberMission));
    }
}
