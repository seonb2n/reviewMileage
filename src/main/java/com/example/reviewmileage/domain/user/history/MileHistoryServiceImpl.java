package com.example.reviewmileage.domain.user.history;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.infrastructures.user.history.MileageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileHistoryServiceImpl implements MileageHistoryService{

    private final MileageHistoryRepository mileageHistoryRepository;

    @Override
    public MileageHistory registerMileageHistory(User user) {
        MileageHistory initMileageHistory = MileageHistory.builder()
                .user(user)
                .build();

        return mileageHistoryRepository.save(initMileageHistory);
    }
}
