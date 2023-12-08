package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.web.dto.MissionRequestDTO;
import com.example.umc_practice.web.dto.MissionResponseDTO;
import com.example.umc_practice.web.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static Mission toMission(MissionRequestDTO.CreateMission requestDTO) {
        return Mission.builder()
                .reward(requestDTO.getReward())
                .missionSpec(requestDTO.getMissionSpec())
                .deadline(requestDTO.getDeadline())
                .memberMissionList(new ArrayList<>())
                .build();
    }

    public static MissionResponseDTO.CreateMissionResultDTO tocreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission){
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList) {
        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(mission -> MissionConverter.missionPreViewDTO(mission)).collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
