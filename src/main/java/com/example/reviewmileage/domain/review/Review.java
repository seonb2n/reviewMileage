package com.example.reviewmileage.domain.review;

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
@Table(name="Review", indexes = {@Index(name="review_token_index", columnList = "review_token")})
public class Review extends BaseEntity {

    private static final String PREFIX_REVIEW = "review_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "review_token")
    private String reviewToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
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

    private boolean isFirstReview;
    private int mileagePoint;

    @Builder
    public Review(User user, Place place, String reviewToken, String content, List<Photo> photos) {
        this.user = user;
        this.place = place;
        isFirstReview = place.getReviewList().size() == 0;
        this.content = content;
        photoList.addAll(photos);
        this.reviewToken = reviewToken;
        calculatePoint();
    }

    public void calculatePoint() {
        mileagePoint = 0;
        if (content.length() >= 1) {
            mileagePoint++;
        }
        if (isFirstReview) {
            mileagePoint++;
        }
        if(photoList.size() >= 1) {
            mileagePoint++;
        }
    }

    public void deleteUser() {
        this.user = null;
    }

    public void updateReview(ReviewCommand.ReviewModCommand reviewUpdateCommand) {
        this.content = reviewUpdateCommand.getContent();
        this.photoList = reviewUpdateCommand.getPhotoList();
        calculatePoint();
    }
}
