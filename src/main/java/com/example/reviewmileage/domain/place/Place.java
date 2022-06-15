package com.example.reviewmileage.domain.place;

import com.example.reviewmileage.common.util.TokenGenerator;
import com.example.reviewmileage.domain.BaseEntity;
import com.example.reviewmileage.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name="Place")
public class Place extends BaseEntity {

    private static final String PREFIX_PLACE = "place_";

    @Id
    @GeneratedValue
    private Long placeId;

    private String placeToken;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "place")
    @JsonManagedReference
    private List<Review> reviewList = new ArrayList<>();

    private String placeName;

    @Builder
    private Place(String placeName) {
        this.placeName = placeName;
        placeToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PLACE);
    }

    private void addReview(Review review) {
        this.reviewList.add(review);
    }
}
