package com.example.reviewmileage.application.place;

import com.example.reviewmileage.domain.place.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceFacade {

    private final PlaceService placeService;

}
