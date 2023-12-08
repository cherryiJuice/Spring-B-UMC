package com.example.umc_practice.apiPayload.exception.handler;

import com.example.umc_practice.apiPayload.code.BaseErrorCode;
import com.example.umc_practice.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
