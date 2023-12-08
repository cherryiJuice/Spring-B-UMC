package com.example.umc_practice.repository;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.enums.MemberStatus;
import com.example.umc_practice.domain.enums.MissionStatus;
import com.example.umc_practice.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    boolean existsByMissionAndMember(Mission mission, Member member);

    Page<MemberMission> findAllByMemberAndStatusIs(Member member, PageRequest pageRequest, MissionStatus missionStatus);
    Page<MemberMission> findAllByMember(Member member, PageRequest pageRequest);
}
