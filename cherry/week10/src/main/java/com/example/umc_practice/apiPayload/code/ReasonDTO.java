package com.example.umc_practice.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ReasonDTO {

    private String message;

    private String code;

    private Boolean isSuccess;

    private HttpStatus httpStatus;

    @Builder
    public ReasonDTO(String message, String code, Boolean isSuccess, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.isSuccess = isSuccess;
        this.httpStatus = httpStatus;
    }
}
