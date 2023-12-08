package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.MissionConverter;
import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.service.missionService.MissionCommandService;
import com.example.umc_practice.service.missionService.MissionQueryService;
import com.example.umc_practice.web.dto.MissionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @Operation(summary = "특정 가게에 미션을 추가하는 API", description = "특정 가게에 미션을 추가하는 API 입니다.")
    @PostMapping("/stores")
    public ApiResponse<?> createMission(@Valid @RequestBody MissionRequestDTO.CreateMission requestDTO) {
        Mission mission = missionCommandService.createMission(requestDTO);
        return ApiResponse.onSuccess(MissionConverter.tocreateMissionResultDTO(mission));
    }

    @Operation(summary = "특정 가게의 미션을 조회하는 API", description = "특정 가게의 미션을 조회하는 API 이며 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "조회할 페이지 번호, query string 입니다.")}
    )
    @GetMapping("/stores/{storeId}")
    public ApiResponse<?> findMission(@PathVariable Long storeId, @RequestParam Integer page) {
        Page<Mission> missionList = missionQueryService.getMissionList(storeId, page-1);
        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
    }
}
