package com.example.umc_practice.service.reviewService;

import com.example.umc_practice.domain.Review;
import com.example.umc_practice.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {

    Review createReview(ReviewRequestDTO.CreateReviewDTO requestDTO);
}
