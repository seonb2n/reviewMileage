package com.example.reviewmileage.infrastructures.review;

import com.example.reviewmileage.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    void deleteByReviewToken(String reviewToken);

    Optional<Review> findReviewByReviewToken(String reviewToken);
}
