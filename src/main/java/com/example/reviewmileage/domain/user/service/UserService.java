package com.example.reviewmileage.domain.user.service;


import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;

public interface UserService {

    UserInfo.Main registerUser(UserCommand.UserRegisterCommand userRegisterCommand);

    UserInfo.Main getUserWithUserToken(String userToken);

    User findUserWithUserToken(String userToken);

    UserInfo.Main updateUser(User user);

    void deleteUser(String userToken);
}
