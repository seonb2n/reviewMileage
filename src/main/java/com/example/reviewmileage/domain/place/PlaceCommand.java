package com.example.reviewmileage.domain.place;

import com.example.reviewmileage.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PlaceCommand {

    @Getter
    @Builder
    @ToString
    public static class PlaceRegisterCommand {
        private String placeName;

        public Place toEntity() {
            return Place.builder()
                    .placeName(placeName)
                    .build();
        }
    }

    @Builder
    @Getter
    @ToString
    public static class PlaceFindCommand {
        private String placeToken;
    }

}
