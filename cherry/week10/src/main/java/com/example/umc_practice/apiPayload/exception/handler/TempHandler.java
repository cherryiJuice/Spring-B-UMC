package com.example.umc_practice.apiPayload.exception.handler;

import com.example.umc_practice.apiPayload.code.BaseErrorCode;
import com.example.umc_practice.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
