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
@Table(name="Photo", indexes = {@Index(name = "photo_token_index", columnList = "photo_token")})
public class Photo extends BaseEntity {


    private static final String PREFIX_PHOTO = "photo_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    @Column(name = "photo_token")
    private String photoToken;

    private String data;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "reviewId")
    private Review review;

    @Builder
    public Photo(String data, String photoToken) {
        this.data = data;
        this.photoToken = photoToken;
    }
}
