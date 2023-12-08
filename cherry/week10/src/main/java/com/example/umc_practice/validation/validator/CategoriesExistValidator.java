package com.example.umc_practice.validation.validator;

import com.example.umc_practice.apiPayload.code.status.ErrorStatus;
import com.example.umc_practice.service.memberService.MemberCommandService;
import com.example.umc_practice.validation.annotation.ExistCategories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<Long>> {

    private final MemberCommandService memberCommandService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
//        boolean isValid = values.stream()
//                .allMatch(value -> foodCategoryRepository.existsById(value));
        boolean isValid = values.stream()
                .allMatch(memberCommandService::isExist);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
