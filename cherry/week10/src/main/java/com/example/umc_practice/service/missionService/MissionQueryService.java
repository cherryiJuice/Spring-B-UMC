package com.example.umc_practice.service.missionService;

import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.repository.MissionRepository;
import com.example.umc_practice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        return missionRepository.findAllByStore(store, PageRequest.of(page, 10));
    }
}
