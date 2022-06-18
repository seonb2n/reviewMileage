package com.example.reviewmileage.application.review;

import com.example.reviewmileage.application.place.PlaceFacade;
import com.example.reviewmileage.application.user.UserFacade;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.ReviewInfo;
import com.example.reviewmileage.domain.review.photos.PhotoService;
import com.example.reviewmileage.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFacade {

    private final ReviewService reviewService;
    private final UserFacade userFacade;
    private final PlaceFacade placeFacade;
    private final PhotoService photoService;

    public ReviewInfo.Main registerReview(ReviewCommand.ReviewRegisterCommand reviewRegisterCommand) {
        var reviewInfo = reviewService.addReview(reviewRegisterCommand);
        var user = reviewRegisterCommand.getUser();
        user.addReview(reviewService.findReviewWithReviewToken(reviewInfo.getReviewToken()));
        userFacade.updateUser(user);
        return reviewInfo;
    }

    public ReviewInfo.Main modReview(ReviewCommand.ReviewModCommand reviewUpdateCommand) {
        var review = reviewService.modReview(reviewUpdateCommand);
        var user = userFacade.findUserWithUserToken(review.getUser().getUserToken());
        userFacade.updateUser(user);
        return review;
    }

    public void deleteReview(ReviewCommand.ReviewDeleteCommand reviewDeleteCommand) {
        //리뷰가 삭제된다.
        //user 의 마일리지를 다시 계산한다
        //user 의 마일리지 변경에 따른 m_history 가 update 된다.
        var review = reviewService.findReviewWithReviewToken(reviewDeleteCommand.getReviewToken());
        var userToken = review.getUser().getUserToken();
        reviewService.deleteReview(reviewDeleteCommand);
        var user = userFacade.findUserWithUserToken(userToken);
        userFacade.updateUser(user);
    }
}
