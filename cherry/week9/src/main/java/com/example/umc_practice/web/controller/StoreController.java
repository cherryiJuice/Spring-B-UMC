package com.example.umc_practice.web.controller;

import com.example.umc_practice.apiPayload.ApiResponse;
import com.example.umc_practice.converter.StoreConverter;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.service.storeService.StoreCommandService;
import com.example.umc_practice.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/stores")
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/regions")
    public ApiResponse<?> createStore(@Valid @RequestBody StoreRequestDTO.createStore requestDTO) {
        Store store = storeCommandService.createStore(requestDTO);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreDTO(store));
    }
}
