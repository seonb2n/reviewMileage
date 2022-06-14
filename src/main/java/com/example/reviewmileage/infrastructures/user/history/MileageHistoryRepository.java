package com.example.reviewmileage.infrastructures.user.history;

import com.example.reviewmileage.domain.user.history.MileageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageHistoryRepository extends JpaRepository <MileageHistory, Long> {
}
