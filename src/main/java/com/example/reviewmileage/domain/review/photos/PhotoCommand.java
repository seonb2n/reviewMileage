package com.example.reviewmileage.domain.review.photos;

import com.example.reviewmileage.domain.place.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PhotoCommand {

    @Getter
    @Builder
    @ToString
    public static class PhotoRegisterCommand {
        private String data;
        private String photoToken;

        public Photo toEntity() {
            return Photo.builder()
                    .data(data)
                    .photoToken(photoToken)
                    .build();
        }
    }

}
