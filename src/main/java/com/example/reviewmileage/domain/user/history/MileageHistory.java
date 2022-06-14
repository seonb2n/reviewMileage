package com.example.reviewmileage.domain.user.history;

import com.example.reviewmileage.common.util.TokenGenerator;
import com.example.reviewmileage.domain.BaseEntity;
import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.user.User;
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
@Table(name="m_history")
public class MileageHistory extends BaseEntity {
    private final String PREFIX_HISTORY = "mHistory_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    private String historyToken;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userId")
    private User user;


    private int mileagePoint;

    @Builder
    public MileageHistory(User user) {
        this.user = user;
        historyToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_HISTORY);
        this.mileagePoint = user.getUserMileagePoint();
    }
}
