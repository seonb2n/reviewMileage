package com.example.reviewmileage.domain.review;
import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.review.photos.Photo;
import com.example.reviewmileage.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class ReviewInfo {

    @Getter
    @ToString
    public static class Main {
        private final String reviewToken;
        private final String content;
        private final List<Photo> photoList;
        private final Place place;
        private final User user;
        private final int mileagePoint;

        public Main(Review review) {
            this.reviewToken = review.getReviewToken();
            this.content = review.getContent();
            this.photoList = review.getPhotoList();
            this.place = review.getPlace();
            this.user = review.getUser();
            this.mileagePoint = review.getMileagePoint();
        }
    }

}
