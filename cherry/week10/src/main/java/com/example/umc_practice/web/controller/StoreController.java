package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.StoreConverter;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.service.storeService.StoreCommandService;
import com.example.umc_practice.service.storeService.StoreQueryService;
import com.example.umc_practice.validation.annotation.ExistStore;
import com.example.umc_practice.web.dto.StoreRequestDTO;
import com.example.umc_practice.web.dto.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/stores")
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @Operation(summary = "특정 지역에 가게 추가 API", description = "특정 지역에 가게를 추가하는 API 입니다.")
    @PostMapping("/regions")
    public ApiResponse<?> createStore(@Valid @RequestBody StoreRequestDTO.createStore requestDTO) {
        Store store = storeCommandService.createStore(requestDTO);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreDTO(store));
    }

    @Operation(summary = "특정 가게에 리뷰 추가 API", description = "특정 가게에 리뷰를 추가하는 API 입니다.")
    @PostMapping("/reviews")
    public ApiResponse<?> createReview(@Valid @RequestBody StoreRequestDTO.ReviewDTO requestDTO) {
        Review review = storeCommandService.createReview(requestDTO);
        return ApiResponse.onSuccess(StoreConverter.createReviewResult(review));
    }

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "조회할 페이지 번호, query string 입니다.")
    })
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page-1);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }


}
