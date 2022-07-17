package com.example.reviewmileage.interfaces.dto;

import com.example.reviewmileage.application.place.PlaceFacade;
import com.example.reviewmileage.application.user.UserFacade;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.photos.PhotoService;
import com.example.reviewmileage.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventDtoMapper {

    private final UserFacade userFacade;
    private final PlaceFacade placeFacade;
    private final PhotoService photoService;

    public ReviewCommand.ReviewRegisterCommand toReviewRegisterCommand(EventDto.EventRequest eventRequest) {
        var photo = photoService.findPhotoWithPhotoToken(eventRequest.getAttachedPhotoIds());
        var user = userFacade.findUserWithUserToken(eventRequest.getUserId());
        var place = placeFacade.findPlaceWithPlaceToken(eventRequest.getPlaceId());

        return ReviewCommand.ReviewRegisterCommand.builder()
                .photoList(photo)
                .user(user)
                .place(place)
                .reviewToken(eventRequest.getReviewId())
                .content(eventRequest.getContent())
                .build();
    }

    public ReviewCommand.ReviewModCommand toReviewModCommand(EventDto.EventRequest eventRequest) {
        var photo = photoService.findPhotoWithPhotoToken(eventRequest.getAttachedPhotoIds());
        return ReviewCommand.ReviewModCommand.builder()
                .reviewToken(eventRequest.getReviewId())
                .photoList(photo)
                .content(eventRequest.getContent())
                .build();
    }

    public ReviewCommand.ReviewDeleteCommand toReviewDeleteCommand(EventDto.EventRequest eventRequest) {
        return ReviewCommand.ReviewDeleteCommand.builder()
                .reviewToken(eventRequest.getReviewId())
                .build();
    }

    public ReviewCommand.ReviewReadCommand toReadCommand(EventDto.EventRequest eventRequest) {
        return ReviewCommand.ReviewReadCommand.builder()
                .reviewToken(eventRequest.getReviewId())
                .build();
    }

    public EventDto.EventResponse toEventResponse(User user) {
        return new EventDto.EventResponse(user);
    }

}
