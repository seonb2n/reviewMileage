package com.example.reviewmileage.domain.place;
import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class PlaceInfo {

    @Getter
    @ToString
    public static class Main {
        private final String placeToken;
        private final String placeName;
        private final List<Review> reviewList;

        public Main(Place place) {
            this.placeToken = place.getPlaceToken();
            this.placeName = place.getPlaceName();
            this.reviewList = place.getReviewList();
        }
    }

}
