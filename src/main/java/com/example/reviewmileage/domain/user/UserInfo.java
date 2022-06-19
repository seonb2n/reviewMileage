package com.example.reviewmileage.domain.user;
import com.example.reviewmileage.domain.review.Review;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class UserInfo {

    @Getter
    @ToString
    public static class Main {
        private final String userToken;
        private final String userName;
        private final int userMileagePoint;
        private final List<Review> reviewList;

        public Main(User user) {
            this.userToken = user.getUserToken();
            this.userName = user.getUserName();
            this.userMileagePoint = user.getUserMileagePoint();
            this.reviewList = user.getReviewList();
        }
    }

}
