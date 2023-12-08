package com.example.umc_practice.web.dto;

import com.example.umc_practice.validation.annotation.ExistRegion;
import com.example.umc_practice.validation.annotation.ExistStore;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreRequestDTO {

    @Getter
    public static class createStore {
        @ExistRegion
        Long regionId;
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;
    }

    @Getter
    public static class ReviewDTO {
        @ExistStore
        Long storeId;
        @Size(min = 10, max = 1000)
        String body;
        @DecimalMin("0.5")
        @DecimalMax("5")
        Double score;
    }
}
