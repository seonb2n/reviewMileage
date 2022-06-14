package com.example.reviewmileage.domain.place.place;

import com.example.reviewmileage.common.exception.TokenNotFoundException;
import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.place.PlaceCommand;
import com.example.reviewmileage.domain.place.PlaceInfo;
import com.example.reviewmileage.infrastructures.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;

    @Override
    public PlaceInfo.Main registerPlace(PlaceCommand.PlaceRegisterCommand placeRegisterCommand) {
        Place initPlace = placeRegisterCommand.toEntity();
        Place place = placeRepository.save(initPlace);
        return new PlaceInfo.Main(place);
    }

    @Override
    public PlaceInfo.Main getPlaceWithPlaceToken(String placeToken) {
        Place place = placeRepository.findPlaceByPlaceToken(placeToken).orElseThrow(TokenNotFoundException::new);
        return new PlaceInfo.Main(place);
    }
}
