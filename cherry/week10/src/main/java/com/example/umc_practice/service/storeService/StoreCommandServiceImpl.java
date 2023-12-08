package com.example.umc_practice.service.storeService;

import com.example.umc_practice.converter.StoreConverter;
import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Region;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.repository.MemberRepository;
import com.example.umc_practice.repository.RegionRepository;
import com.example.umc_practice.repository.ReviewRepository;
import com.example.umc_practice.repository.StoreRepository;
import com.example.umc_practice.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Review createReview(StoreRequestDTO.ReviewDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreId()).get();
        Member member = memberRepository.findById(1L).get();
        Review review = StoreConverter.toReview(requestDTO);
        Double avgScore = calculateAverageRating(review, store);
        store.updateScore(avgScore);
        review.setMember(member);
        review.setStore(store);
        return reviewRepository.save(review);
    }

    private Double calculateAverageRating(Review review, Store store) {
        List<Review> reviews = reviewRepository.findAllByStore(store).get();
        return (store.getScore() * reviews.size() + review.getScore()) / (reviews.size()+1);
    }

    @Transactional
    @Override
    public Store createStore(StoreRequestDTO.createStore requestDTO) {
        Region region = regionRepository.findById(requestDTO.getRegionId()).get();
        Store store = StoreConverter.toStore(requestDTO);
        store.setRegion(region);
        return storeRepository.save(store);
    }
}
