package com.example.umc_practice.converter;

import com.example.umc_practice.domain.Store;
import com.example.umc_practice.web.dto.StoreRequestDTO;
import com.example.umc_practice.web.dto.StoreResponseDTO;

import java.util.ArrayList;

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
}
