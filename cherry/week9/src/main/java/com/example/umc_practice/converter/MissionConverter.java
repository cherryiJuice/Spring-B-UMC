package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.web.dto.MissionRequestDTO;
import com.example.umc_practice.web.dto.MissionResponseDTO;

import java.util.ArrayList;

public class MissionConverter {
    public static Mission toMission(MissionRequestDTO.CreateMission requestDTO) {
        return Mission.builder()
                .reward(requestDTO.getReward())
                .missionSpec(requestDTO.getMissionSpec())
                .deadline(requestDTO.getDeadline())
                .memberMissionList(new ArrayList<>())
                .build();
    }

    public static MissionResponseDTO.CreateMissionResultDto tocreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDto.builder()
                .missionId(mission.getId())
                .createAt(mission.getCreatedAt())
                .build();
    }
}
