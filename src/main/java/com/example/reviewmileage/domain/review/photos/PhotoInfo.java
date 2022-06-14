package com.example.reviewmileage.domain.review.photos;
import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.review.Review;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class PhotoInfo {

    @Getter
    @ToString
    public static class Main {
        private final String photoToken;
        private final String data;

        public Main(Photo photo) {
            this.photoToken = photo.getPhotoToken();
            this.data = photo.getData();
        }
    }

}
