package com.example.umc_practice.validation.validator;

import com.example.umc_practice.apiPayload.code.status.ErrorStatus;
import com.example.umc_practice.service.memberMissionService.MemberMissionCommandService;
import com.example.umc_practice.validation.annotation.ChallengingMission;
import com.example.umc_practice.web.dto.MemberMissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<ChallengingMission, MemberMissionRequestDTO.CreateMemberMission> {

    private final MemberMissionCommandService memberMissionCommandService;

    @Override
    public void initialize(ChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDTO.CreateMemberMission value, ConstraintValidatorContext context) {
        boolean isValid = memberMissionCommandService.existsMemberMission(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_EXIST_MEMBER_MISSION.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }

}
