package com.example.reviewmileage.infrastructures.user;

import com.example.reviewmileage.common.exception.TokenNotFoundException;
import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.service.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;

    @Override
    public User getUserWithUserToken(String userToken) {
        return userRepository.findUserByUserToken(userToken)
                .orElseThrow(TokenNotFoundException::new);
    }
}
