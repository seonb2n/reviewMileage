package com.example.reviewmileage.domain.review.service;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.ReviewInfo;

public interface ReviewService {

    ReviewInfo.Main addReview(ReviewCommand.ReviewRegisterCommand reviewRegisterCommand);

    ReviewInfo.Main modReview(ReviewCommand.ReviewModCommand reviewUpdateCommand);

    void deleteReview(ReviewCommand.ReviewDeleteCommand reviewDeleteCommand);

    Review findReviewWithReviewToken(String reviewToken);

    Review readReview(ReviewCommand.ReviewReadCommand command);
}
