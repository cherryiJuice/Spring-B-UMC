package com.example.umc_practice.service.memberService;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.repository.MemberMissionRepository;
import com.example.umc_practice.repository.MemberRepository;
import com.example.umc_practice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.example.umc_practice.domain.enums.MissionStatus.CHALLENGING;
import static com.example.umc_practice.domain.enums.MissionStatus.COMPLETE;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }
}
