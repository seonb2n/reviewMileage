package com.example.reviewmileage.interfaces.dto;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserInfo;
import com.example.reviewmileage.domain.user.history.MileageHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class EventDto {

    @Getter
    @Setter
    @ToString
    @Builder
    public static class EventRequest {
        private String type;
        private String action;
        private String reviewId;
        private String content;
        private List<String> attachedPhotoIds;
        private String userId;
        private String placeId;
    }

    @Getter
    @Setter
    @ToString
    public static class EventResponse {
        int userMileagePoint;
        String userToken;
        List<Review> reviewList;

        public EventResponse(User user) {
            this.userMileagePoint = user.getUserMileagePoint();
            this.userToken = user.getUserToken();
            this.reviewList = user.getReviewList();
        }
    }

}
