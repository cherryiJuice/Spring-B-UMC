package com.example.umc_practice.repository;

import com.example.umc_practice.domain.Mission;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
