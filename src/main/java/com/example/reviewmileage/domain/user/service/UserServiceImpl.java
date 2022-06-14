package com.example.reviewmileage.domain.user.service;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserStore userStore;
    private final UserReader userReader;

    @Override
    public UserInfo.Main registerUser(UserCommand.UserRegisterCommand userRegisterCommand) {
        User initUser = userRegisterCommand.toEntity();
        User user =  userStore.registerUser(initUser);
        return new UserInfo.Main(user);
    }

    @Override
    public UserInfo.Main getUserWithUserToken(String userToken) {
       User user = userReader.getUserWithUserToken(userToken);
       return new UserInfo.Main(user);
    }
}
