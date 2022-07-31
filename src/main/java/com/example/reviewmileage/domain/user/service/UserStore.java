package com.example.reviewmileage.domain.user.service;

import com.example.reviewmileage.domain.user.User;

public interface UserStore {

    User store(User user);

    User registerUser(User initUser);

    void deleteUser(String userToken);
}
