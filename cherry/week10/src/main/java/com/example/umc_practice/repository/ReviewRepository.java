package com.example.umc_practice.repository;

import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.Review;
import com.example.umc_practice.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<List<Review>> findAllByStore(Store store);
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);

}
