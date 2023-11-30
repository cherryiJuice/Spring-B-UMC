package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {
    public static MemberMission toMemberMission(Mission mission) {
        return MemberMission.builder()
                .mission(mission)
                .build();
    }

    public static MemberMissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.AddMissionResultDTO.builder()
                .MemberMissionId(memberMission.getId())
                .createAt(memberMission.getCreatedAt())
                .build();
    }
}
