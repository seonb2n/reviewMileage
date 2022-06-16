package com.example.reviewmileage.infrastructures.review;

import com.example.reviewmileage.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Transactional
    void deleteReviewByReviewToken(String reviewToken);

    @Query("select r from Review r left join fetch r.photoList where r.reviewToken= :reviewToken")
    Optional<Review> findReviewByReviewToken(String reviewToken);
}
