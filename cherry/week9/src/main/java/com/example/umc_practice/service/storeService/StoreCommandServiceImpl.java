package com.example.umc_practice.service.storeService;

import com.example.umc_practice.converter.StoreConverter;
import com.example.umc_practice.domain.Region;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.repository.RegionRepository;
import com.example.umc_practice.repository.StoreRepository;
import com.example.umc_practice.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    @Override
    public Store createStore(StoreRequestDTO.createStore requestDTO) {
        Region region = regionRepository.findById(requestDTO.getRegionId()).get();
        Store store = StoreConverter.toStore(requestDTO);
        store.setRegion(region);
        return storeRepository.save(store);
    }
}
