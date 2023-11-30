package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.ReviewConverter;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.service.reviewService.ReviewCommandService;
import com.example.umc_practice.web.dto.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/stores")
    public ApiResponse<?> createReview(@Valid @RequestBody ReviewRequestDTO.CreateReviewDTO requestDTO) {
        Review review = reviewCommandService.createReview(requestDTO);
        return ApiResponse.onSuccess(ReviewConverter.createReviewResult(review));
    }
}
