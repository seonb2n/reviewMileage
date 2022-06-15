package com.example.reviewmileage.domain.review.service;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.ReviewInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewReader reviewReader;
    private final ReviewStore reviewStore;

    @Override
    public ReviewInfo.Main addReview(ReviewCommand.ReviewRegisterCommand reviewRegisterCommand) {
        Review initReview = reviewRegisterCommand.toEntity();
        Review review = reviewStore.registerReview(initReview);
        return new ReviewInfo.Main(review);
    }

    @Override
    public ReviewInfo.Main modReview(ReviewCommand.ReviewUpdateCommand reviewUpdateCommand) {
        Review review = reviewReader.getReviewWithReviewToken(reviewUpdateCommand.getReviewToken());
        review.updateReview(reviewUpdateCommand);
        return new ReviewInfo.Main(reviewStore.store(review));
    }

    @Override
    public void deleteReview(ReviewCommand.ReviewDeleteCommand reviewDeleteCommand) {
        reviewStore.delete(reviewDeleteCommand.getReviewToken());
    }

    @Override
    public Review findReviewWithReviewToken(String reviewToken) {
        return reviewReader.getReviewWithReviewToken(reviewToken);
    }
}
