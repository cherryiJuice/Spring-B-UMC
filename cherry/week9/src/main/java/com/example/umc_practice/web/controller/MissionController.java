package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.MissionConverter;
import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.service.missionService.MissionCommandService;
import com.example.umc_practice.web.dto.MissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/stores")
    public ApiResponse<?> createMission(@Valid @RequestBody MissionRequestDTO.CreateMission requestDTO) {
        Mission mission = missionCommandService.createMission(requestDTO);
        return ApiResponse.onSuccess(MissionConverter.tocreateMissionResultDTO(mission));
    }
}
