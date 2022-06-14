package com.example.reviewmileage.domain.review;

import com.example.reviewmileage.common.util.TokenGenerator;
import com.example.reviewmileage.domain.BaseEntity;
import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.review.photos.Photo;
import com.example.reviewmileage.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="Review")
public class Review extends BaseEntity {

    private static final String PREFIX_REVIEW = "review_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String reviewToken;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Photo> photoList = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "placeId")
    private Place place;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userId")
    private User user;

    private String content;

    private int mileagePoint;

    @Builder
    public Review(User user, Place place, String content, Photo... photos) {
        this.user = user;
        this.place = place;
        this.content = content;
        photoList.addAll(List.of(photos));
        this.reviewToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_REVIEW);
        mileagePoint = 0;
        calculatePoint();
    }

    public void calculatePoint() {
        if (content.length() >= 1) {
            mileagePoint++;
        }
        if (place.getReviewList().size() == 1) {
            mileagePoint++;
        }
        if(photoList.size() >= 1) {
            mileagePoint++;
        }
    }
}
