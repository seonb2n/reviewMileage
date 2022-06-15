package com.example.reviewmileage.application.place;

import com.example.reviewmileage.domain.place.PlaceCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlaceFacadeTest {

    @Autowired
    private PlaceFacade placeFacade;

    /**
     * 장소 등록 및 조회 테스트
     */

    @Test
    public void registerPlaceTest (){
        PlaceCommand.PlaceRegisterCommand placeRegisterCommand = PlaceCommand.PlaceRegisterCommand.builder()
                .placeName("test-place")
                .build();

        var place = placeFacade.registerPlace(placeRegisterCommand);
        var foundPlace = placeFacade.getPlaceInfoWithPlaceFindCommand(PlaceCommand.PlaceFindCommand.builder()
                .placeToken(place.getPlaceToken())
                .build());

        Assertions.assertEquals(place.getPlaceName(), foundPlace.getPlaceName());
    }

}