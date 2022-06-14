package com.example.reviewmileage.application.user;

import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import com.example.reviewmileage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;

    public UserInfo.Main registerUser(UserCommand.UserRegisterCommand userRegisterCommand) {
        return userService.registerUser(userRegisterCommand);
    }

    public UserInfo.Main findUserWithUserToken(UserCommand.UserFindCommand userFindCommand) {
        return userService.getUserWithUserToken(userFindCommand.getUserToken());
    }
}
