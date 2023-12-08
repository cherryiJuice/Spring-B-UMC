package com.example.umc_practice.service.memberService;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDTO request);

    boolean isExist(Long value);

}
