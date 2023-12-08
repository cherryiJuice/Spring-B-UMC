package com.example.umc_practice.service.memberMissionService;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.enums.MissionStatus;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.repository.MemberMissionRepository;
import com.example.umc_practice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Page<MemberMission> getChallengingMemberMission(Long memberId, String status, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        if(status.equals("0")){
            return memberMissionRepository.findAllByMemberAndStatusIs(member, PageRequest.of(page, 10), MissionStatus.CHALLENGING);
        }
        else if(status.equals("1")){
            return memberMissionRepository.findAllByMemberAndStatusIs(member, PageRequest.of(page, 10), MissionStatus.COMPLETE);
        }
        else {
            return memberMissionRepository.findAllByMember(member, PageRequest.of(page, 10));
        }
    }
}
