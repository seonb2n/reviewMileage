package com.example.reviewmileage.domain.review;

import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.review.photos.Photo;
import com.example.reviewmileage.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class ReviewCommand {

    @Getter
    @Builder
    @ToString
    public static class ReviewRegisterCommand {
        private User user;
        private Place place;
        private String content;
        private String reviewToken;
        private List<Photo> photoList;

        public Review toEntity() {
            return Review.builder()
                    .reviewToken(reviewToken)
                    .place(place)
                    .user(user)
                    .content(content)
                    .photos(photoList)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ReviewModCommand {
        private String reviewToken;
        private String content;
        private List<Photo> photoList;
    }


    @Getter
    @Builder
    @ToString
    public static class ReviewDeleteCommand {
        private String reviewToken;
    }
}
