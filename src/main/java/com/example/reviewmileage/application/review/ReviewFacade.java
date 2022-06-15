package com.example.reviewmileage.application.review;

import com.example.reviewmileage.application.place.PlaceFacade;
import com.example.reviewmileage.application.user.UserFacade;
import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.ReviewInfo;
import com.example.reviewmileage.domain.review.service.ReviewService;
import com.example.reviewmileage.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewFacade {

    private final ReviewService reviewService;
    private final UserFacade userFacade;
    private final PlaceFacade placeFacade;

    public ReviewInfo.Main registerReview(ReviewCommand.ReviewRegisterCommand reviewRegisterCommand) {
        ReviewInfo.Main review = reviewService.addReview(reviewRegisterCommand);
        userFacade.updateUser(reviewService.findReviewWithReviewToken(review.getReviewToken()));
        return review;
    }
}
