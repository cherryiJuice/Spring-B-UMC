package com.example.umc_practice.service.missionService;

import com.example.umc_practice.converter.MissionConverter;
import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.repository.MissionRepository;
import com.example.umc_practice.repository.StoreRepository;
import com.example.umc_practice.web.dto.MissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public Mission createMission(MissionRequestDTO.CreateMission requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreId()).get();
        Mission mission = MissionConverter.toMission(requestDTO);
        mission.setStore(store);
        return missionRepository.save(mission);
    }
}
