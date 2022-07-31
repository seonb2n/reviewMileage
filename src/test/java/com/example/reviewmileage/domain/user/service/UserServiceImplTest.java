package com.example.reviewmileage.domain.user.service;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.infrastructures.user.UserReaderImpl;
import com.example.reviewmileage.infrastructures.user.UserRepository;
import com.example.reviewmileage.infrastructures.user.UserStoreImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl userService;

    @MockBean(name = "userStore")
    private UserStoreImpl userStore;

    @MockBean(name = "userReader")
    private UserReaderImpl userReader;

    private User mockUserEntity;

    @BeforeEach
    private void beforeEach() {
        mockUserEntity = User.builder()
                .userName("mock-user")
                .build();
        userService = new UserServiceImpl(userStore, userReader);
    }

    @Test
    void registerUser() {
        //init
        var userCommand = UserCommand.UserRegisterCommand.builder()
                .userName("mock-user")
                        .build();

        //given
        given(userStore.registerUser(any())).willReturn(mockUserEntity);

        //when
        var registeredUser = userService.registerUser(userCommand);


        //then
        assertEquals("mock-user", registeredUser.getUserName());

    }

    @Test
    void deleteUser() {
    }
}