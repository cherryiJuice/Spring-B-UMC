package com.example.umc_practice.web.dto;

import com.example.umc_practice.validation.annotation.ChallengingMission;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class MemberMissionRequestDTO {

    @ChallengingMission
    @Getter
    public static class CreateMemberMission {

        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }
}
