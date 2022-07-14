package com.example.reviewmileage.infrastructures.review;

import com.example.reviewmileage.common.exception.TokenNotFoundException;
import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.service.ReviewReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewReaderImpl implements ReviewReader {

    private final ReviewRepository reviewRepository;

    @Override
    public Review getReviewWithReviewToken(String reviewToken) {
        var review =  reviewRepository.findReviewByReviewToken(reviewToken).orElseThrow(TokenNotFoundException::new);
        return  review;
    }
}
