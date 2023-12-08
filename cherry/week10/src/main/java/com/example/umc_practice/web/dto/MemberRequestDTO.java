package com.example.umc_practice.web.dto;

import com.example.umc_practice.validation.annotation.ExistCategories;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

public class MemberRequestDTO {


    @Getter
    public static class JoinDTO {

        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull @Range(min = 1, max = 12)
        Integer birthMonth;
        @NotNull @Range(min = 1, max = 31)
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }
}
