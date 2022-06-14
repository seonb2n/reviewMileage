package com.example.reviewmileage.infrastructures.review;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.service.ReviewStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewStoreImpl implements ReviewStore {

    private final ReviewRepository reviewRepository;

    @Override
    public Review store(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review registerReview(Review initReview) {
        return reviewRepository.save(initReview);
    }

    @Override
    public void delete(String reviewToken) {
        reviewRepository.deleteByReviewToken(reviewToken);
    }
}
