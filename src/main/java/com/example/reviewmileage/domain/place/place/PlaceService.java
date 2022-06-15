package com.example.reviewmileage.domain.place.place;


import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.place.PlaceCommand;
import com.example.reviewmileage.domain.place.PlaceInfo;

public interface PlaceService {

    PlaceInfo.Main registerPlace(PlaceCommand.PlaceRegisterCommand placeRegisterCommand);

    PlaceInfo.Main getPlaceWithPlaceToken(String placeToken);

    Place findPlaceWithPlaceToken(String placeToken);
}
