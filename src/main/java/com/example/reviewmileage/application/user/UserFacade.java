package com.example.reviewmileage.application.user;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import com.example.reviewmileage.domain.user.history.MileageHistory;
import com.example.reviewmileage.domain.user.history.MileageHistoryService;
import com.example.reviewmileage.domain.user.service.UserService;
import com.example.reviewmileage.domain.user.service.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final UserStore userStore;
    private final MileageHistoryService mileageHistoryService;

    public UserInfo.Main registerUser(UserCommand.UserRegisterCommand userRegisterCommand) {
        return userService.registerUser(userRegisterCommand);
    }

    public UserInfo.Main getUserInfoWithUserFindCommand(UserCommand.UserFindCommand userFindCommand) {
        return userService.getUserWithUserToken(userFindCommand.getUserToken());
    }

    public User findUserWithUserToken(String userToken) {
        return userService.findUserWithUserToken(userToken);
    }

    @Transactional
    public void updateUser(Review review) {
        User user = review.getUser();
        int nowMileage = user.getUserMileagePoint();
        int newMileage = user.updateMileage();
        if(nowMileage != newMileage) {
            mileageHistoryService.registerMileageHistory(user);
        }
        userStore.store(user);
    }
}
