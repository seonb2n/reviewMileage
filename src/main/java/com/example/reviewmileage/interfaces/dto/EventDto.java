package com.example.reviewmileage.interfaces.dto;

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

        public EventResponse() {

        }
    }

}
