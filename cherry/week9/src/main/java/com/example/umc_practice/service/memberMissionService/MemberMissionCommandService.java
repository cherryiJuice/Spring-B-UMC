package com.example.umc_practice.service.memberMissionService;

import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
    public MemberMission addMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO);

    public boolean existsMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO);
}
