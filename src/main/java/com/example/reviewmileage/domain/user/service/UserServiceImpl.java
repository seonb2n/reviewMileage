package com.example.reviewmileage.domain.user.service;

import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.service.ReviewService;
import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserStore userStore;
    private final UserReader userReader;
    private final ReviewService reviewService;

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
        setReviewAnonymous(userToken);
        userStore.deleteUser(userToken);
    }

    @CacheEvict(value = "reviewCache", allEntries = true)
    public void setReviewAnonymous(String userToken) {
        User user = userReader.getUserWithUserToken(userToken);
        var reviewList = user.getReviewList();
        for (Review review : reviewList) {
            //각각의 review 에 대한 익명 처리 로직..
        }
    }



    public byte[] userSerializer(User user) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(user);
            return baos.toByteArray();
        }
    }

    public byte[] userSerializerWithUtils(User user) {
        return SerializationUtils.serialize(user);
    }

    public User userDeserializer(byte[] userBytes) throws Exception {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(userBytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            Object object = ois.readObject();
            User user = (User) object;
            return user;
        }
    }

    public User userDeserializerWithUtils(byte[] userBytes) {
        return SerializationUtils.deserialize(userBytes);
    }

}
