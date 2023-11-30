package com.example.umc_practice.web.dto;

import com.example.umc_practice.validation.annotation.ExistStore;
import lombok.Getter;

import javax.validation.constraints.*;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDTO {
        @ExistStore
        Long storeId;
        @Size(min = 10, max = 1000)
        String body;
        @DecimalMin("0.5")
        @DecimalMax("5")
        Double score;
    }
}
