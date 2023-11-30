package com.example.umc_practice.service.memberMissionService;

import com.example.umc_practice.converter.MemberMissionConverter;
import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.repository.MemberMissionRepository;
import com.example.umc_practice.repository.MemberRepository;
import com.example.umc_practice.repository.MissionRepository;
import com.example.umc_practice.web.dto.MemberMissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    @Override
    public MemberMission addMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO) {
        Member member = memberRepository.findById(requestDTO.getMemberId()).get();
        Mission mission = missionRepository.findById(requestDTO.getMissionId()).get();

        MemberMission memberMission = MemberMissionConverter.toMemberMission(mission);
        memberMission.setMember(member);
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public boolean existsMemberMission(MemberMissionRequestDTO.CreateMemberMission requestDTO) {
        Member member = memberRepository.findById(requestDTO.getMemberId()).get();
        Mission mission = missionRepository.findById(requestDTO.getMissionId()).get();
        return !memberMissionRepository.existsByMissionAndMember(mission, member);
    }
}
