package com.example.reviewmileage.infrastructures.user;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.service.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;

    @Override
    public User store(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registerUser(User initUser) {
        return userRepository.save(initUser);
    }
}
