package com.example.umc_practice.service.reviewService;

import com.example.umc_practice.converter.ReviewConverter;
import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import com.example.umc_practice.repository.MemberRepository;
import com.example.umc_practice.repository.ReviewRepository;
import com.example.umc_practice.repository.StoreRepository;
import com.example.umc_practice.web.dto.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public Review createReview(ReviewRequestDTO.CreateReviewDTO requestDTO) {
        Store store = storeRepository.findById(requestDTO.getStoreId()).get();
        Member member = memberRepository.findById(1L).get();
        Review review = ReviewConverter.toReview(requestDTO);
        log.info("합치기 전 별점={}", store.getScore());
        Double avgScore = calculateAverageRating(review, store);
        store.updateScore(avgScore);
        log.info("별점={}", store.getScore());
        review.setMember(member);
        review.setStore(store);
        return reviewRepository.save(review);
    }

    private Double calculateAverageRating(Review review, Store store) {
        List<Review> reviews = reviewRepository.findAllByStore(store).get();
        log.info("{}", store.getScore());
        log.info("{}", reviews.size());
        return (store.getScore() * reviews.size() + review.getScore()) / (reviews.size()+1);
    }
}
