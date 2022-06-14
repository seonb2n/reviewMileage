package com.example.reviewmileage.domain.review.photos;

import com.example.reviewmileage.common.util.TokenGenerator;
import com.example.reviewmileage.domain.BaseEntity;
import com.example.reviewmileage.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name="Photo")
public class Photo extends BaseEntity {

    private static final String PREFIX_PHOTO = "photo_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    private String photoToken;

    private String data;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "reviewId")
    private Review review;

    @Builder
    public Photo(String data) {
        this.data = data;
        photoToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PHOTO);
    }
}