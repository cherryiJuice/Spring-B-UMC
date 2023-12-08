package com.example.umc_practice.service.storeService;

import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.repository.ReviewRepository;
import com.example.umc_practice.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
@RequiredArgsConstructor
public class StoreQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
}
