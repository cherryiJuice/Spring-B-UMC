package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.enums.Gender;
import com.example.umc_practice.domain.mapping.MemberMission;
import com.example.umc_practice.web.dto.MemberRequestDTO;
import com.example.umc_practice.web.dto.MemberResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDto toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDto.builder()
                .memberId(member.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request) {
        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .address(request.getAddress())
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .birthday(LocalDate.of(request.getBirthYear(), request.getBirthMonth(), request.getBirthDay()))
                .age((LocalDate.now().getYear())-request.getBirthYear())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<MemberResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(review -> MemberConverter.reviewPreViewDTO(review)).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static MemberResponseDTO.MissionPreViewDTO missionPreViewDTO(MemberMission memberMission){
        return MemberResponseDTO.MissionPreViewDTO.builder()
                .status(memberMission.getStatus().toString())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    public static MemberResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> missionList) {
        List<MemberResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(memberMission -> MemberConverter.missionPreViewDTO(memberMission)).collect(Collectors.toList());

        return MemberResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

}
