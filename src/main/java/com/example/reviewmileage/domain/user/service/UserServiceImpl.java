package com.example.reviewmileage.domain.user.service;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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
    @Transactional(readOnly = true)
    public UserInfo.Main getUserWithUserToken(String userToken) {
       User user = userReader.getUserWithUserToken(userToken);
       return new UserInfo.Main(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserWithUserToken(String userToken) {
        return userReader.getUserWithUserToken(userToken);
    }

    @Override
    public UserInfo.Main updateUser(User user) {
        User savedUser = userStore.store(user);
        return new UserInfo.Main(savedUser);
    }

    @Override
    public void deleteUser(String userToken) {
        userStore.deleteUser(userToken);
    }
}
