package com.example.umc_practice.service.storeService;

import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store createStore(StoreRequestDTO.createStore requestDTO);
    Review createReview(StoreRequestDTO.ReviewDTO requestDTO);
}
