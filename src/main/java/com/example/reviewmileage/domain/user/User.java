package com.example.reviewmileage.domain.user;

import com.example.reviewmileage.common.util.TokenGenerator;
import com.example.reviewmileage.domain.BaseEntity;
import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.user.history.MileageHistory;
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
@Table(name="user", indexes = {@Index(name = "user_token_index", columnList = "user_token")})
public class User extends BaseEntity {

    private static final String PREFIX_USER = "user_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_token")
    private String userToken;

    private String userName;

    private int userMileagePoint;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<MileageHistory> mileageHistoryList = new ArrayList<>();

    @Builder
    private User(String userName) {
        this.userName = userName;
        userToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_USER);
        userMileagePoint = 0;
    }

    public void deleteReview(Review review) {
        this.reviewList.remove(review);
    }

    public void addReview(Review review) {
        this.reviewList.add(review);
    }

    public int updateMileage() {
        int recentMileage = 0;
        for(Review r : reviewList) {
            recentMileage += r.getMileagePoint();
        }
        this.userMileagePoint = recentMileage;
        return userMileagePoint;
    }

    public String updateUserToken(String newUserToken) {
        this.userToken = newUserToken;
        return newUserToken;
    }
}
