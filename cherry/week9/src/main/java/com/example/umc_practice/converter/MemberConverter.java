package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.enums.Gender;
import com.example.umc_practice.web.dto.MemberRequestDTO;
import com.example.umc_practice.web.dto.MemberResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
}
