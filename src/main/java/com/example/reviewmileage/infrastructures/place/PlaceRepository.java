package com.example.reviewmileage.infrastructures.place;

import com.example.reviewmileage.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findPlaceByPlaceToken(String placeToken);

}
