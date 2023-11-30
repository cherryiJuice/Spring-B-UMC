package com.example.umc_practice.web.dto;

import com.example.umc_practice.validation.annotation.ExistRegion;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
}
