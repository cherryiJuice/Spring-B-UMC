package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.web.dto.ReviewRequestDTO;
import com.example.umc_practice.web.dto.ReviewResponseDTO;

import java.util.ArrayList;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .reviewImagesList(new ArrayList<>())
                .build();
    }

    public static ReviewResponseDTO.CreateReviewDto createReviewResult(Review review) {
        return ReviewResponseDTO.CreateReviewDto.builder()
                .reviewId(review.getId())
                .createAt(review.getCreatedAt())
                .build();
    }
}
