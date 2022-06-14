package com.example.reviewmileage.domain.user.service;
import com.example.reviewmileage.domain.user.User;

public interface UserReader {

    User getUserWithUserToken(String userToken);
}
