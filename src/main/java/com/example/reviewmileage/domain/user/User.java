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
@Table(name="user")
public class User extends BaseEntity {

    private static final String PREFIX_USER = "user_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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

    public int updateMileage() {
        int recentMileage = 0;
        for(Review r : reviewList) {
            recentMileage += r.getMileagePoint();
        }
        this.userMileagePoint = recentMileage;
        return userMileagePoint;
    }
}
