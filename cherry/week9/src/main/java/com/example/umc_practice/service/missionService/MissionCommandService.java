package com.example.umc_practice.service.missionService;


import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    Mission createMission(MissionRequestDTO.CreateMission requestDTO);
}
