package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.MemberConverter;
import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.service.memberService.MemberCommandService;
import com.example.umc_practice.service.memberService.MemberQueryService;
import com.example.umc_practice.web.dto.MemberRequestDTO;
import com.example.umc_practice.web.dto.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "회원 가입 API", description = "회원 가입 API 입니다.")
    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @Operation(summary = "사용자가 작성한 리뷰 조회 API", description = "사용자가 작성한 리뷰를 조회하는 API 이며 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/reviews")
    public ApiResponse<?> findReview(@RequestParam Integer page) {
        Page<Review> reviewList = memberQueryService.getReviewList(1L, page);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));
    }
}
