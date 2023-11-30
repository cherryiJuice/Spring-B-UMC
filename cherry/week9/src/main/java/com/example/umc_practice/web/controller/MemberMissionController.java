package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.MemberMissionConverter;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.service.memberMissionService.MemberMissionCommandService;
import com.example.umc_practice.web.dto.MemberMissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMissions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    public ApiResponse<?> addMemberMission(@Valid @RequestBody MemberMissionRequestDTO.CreateMemberMission requestDTO) {
        MemberMission memberMission = memberMissionCommandService.addMemberMission(requestDTO);
        return ApiResponse.onSuccess(MemberMissionConverter.toAddMissionResultDTO(memberMission));
    }
}
