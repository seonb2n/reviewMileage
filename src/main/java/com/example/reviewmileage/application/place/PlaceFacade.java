package com.example.reviewmileage.application.place;

import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.place.PlaceCommand;
import com.example.reviewmileage.domain.place.PlaceInfo;
import com.example.reviewmileage.domain.place.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceFacade {

    private final PlaceService placeService;

    public PlaceInfo.Main registerPlace(PlaceCommand.PlaceRegisterCommand placeRegisterCommand) {
        return placeService.registerPlace(placeRegisterCommand);
    }

    public PlaceInfo.Main getPlaceInfoWithPlaceFindCommand(PlaceCommand.PlaceFindCommand placeFindCommand) {
        return placeService.getPlaceWithPlaceToken(placeFindCommand.getPlaceToken());
    }

    public Place findPlaceWithPlaceToken(String placeToken) {
        return placeService.findPlaceWithPlaceToken(placeToken);
    }

}
