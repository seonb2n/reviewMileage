package com.example.reviewmileage.domain.review.service;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.ReviewInfo;
import com.example.reviewmileage.domain.review.photos.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService{

    private final ReviewReader reviewReader;
    private final ReviewStore reviewStore;
    private final PhotoService photoService;

    @Override
    @CacheEvict(value = "reviewCache", key = "#reviewRegisterCommand.reviewToken")
    public ReviewInfo.Main addReview(ReviewCommand.ReviewRegisterCommand reviewRegisterCommand) {
        Review initReview = reviewRegisterCommand.toEntity();
        Review review = reviewStore.registerReview(initReview);
        return new ReviewInfo.Main(review);
    }

    @Override
    @CacheEvict(value = "reviewCache", key = "#reviewUpdateCommand.reviewToken")
    public ReviewInfo.Main modReview(ReviewCommand.ReviewModCommand reviewUpdateCommand) {
        Review review = reviewReader.getReviewWithReviewToken(reviewUpdateCommand.getReviewToken());
        review.updateReview(reviewUpdateCommand);
        return new ReviewInfo.Main(reviewStore.store(review));
    }

    @Override
    @CacheEvict(value = "reviewCache", key = "#reviewDeleteCommand.reviewToken")
    public void deleteReview(ReviewCommand.ReviewDeleteCommand reviewDeleteCommand) {
        reviewStore.delete(reviewDeleteCommand.getReviewToken());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "reviewCache", key = "#reviewToken")
    public Review findReviewWithReviewToken(String reviewToken) {
        return reviewReader.getReviewWithReviewToken(reviewToken);
    }
}
