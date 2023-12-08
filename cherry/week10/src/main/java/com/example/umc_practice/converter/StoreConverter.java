package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.web.dto.StoreRequestDTO;
import com.example.umc_practice.web.dto.StoreResponseDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.createStore requestDTO) {
        return Store.builder()
                .name(requestDTO.getName())
                .address(requestDTO.getAddress())
                .missionList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .build();
    }

    public static StoreResponseDTO.JoinResultDto toCreateStoreDTO(Store store) {
        return StoreResponseDTO.JoinResultDto.builder()
                .storeId(store.getId())
                .createAt(store.getCreatedAt())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .reviewImagesList(new ArrayList<>())
                .build();
    }

    public static StoreResponseDTO.CreateReviewDto createReviewResult(Review review) {
        return StoreResponseDTO.CreateReviewDto.builder()
                .reviewId(review.getId())
                .createAt(review.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(review -> StoreConverter.reviewPreViewDTO(review)).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
