package com.example.reviewmileage.domain.user.service;

import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.infrastructures.user.UserReaderImpl;
import com.example.reviewmileage.infrastructures.user.UserRepository;
import com.example.reviewmileage.infrastructures.user.UserStoreImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private UserStoreImpl userStore;

    @Mock
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
        given(userStore.registerUser(any())).willAnswer(invocation -> {
            var user = (User) invocation.getArgument(0);

            assertEquals("mock-user", user.getUserName());

            return mockUserEntity;
                }
        );

        //when
        var registeredUser = userService.registerUser(userCommand);

        //then
        assertEquals("mock-user", registeredUser.getUserName());

    }

    @Test
    void deleteUser() {
        //init
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        //given

        //when
        userService.deleteUser(mockUserEntity.getUserToken());

        //then
        verify(userStore, atLeastOnce()).deleteUser(any());
        verify(userStore).deleteUser(argumentCaptor.capture());

        String token = argumentCaptor.getValue();
        assertEquals(token, mockUserEntity.getUserToken());
    }
}