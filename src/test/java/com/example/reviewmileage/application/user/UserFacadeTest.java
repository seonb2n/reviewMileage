package com.example.reviewmileage.application.user;

import com.example.reviewmileage.domain.user.UserCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserFacadeTest {

    @Autowired
    private UserFacade userFacade;

    /**
     * 사용자 등록 및 조회 테스트
     */
    @Test
    public void registerUserTest() {
        UserCommand.UserRegisterCommand userRegisterCommand = UserCommand.UserRegisterCommand.builder()
                .userName("test-user")
                .build();

        var user = userFacade.registerUser(userRegisterCommand);

        var foundUser = userFacade.getUserInfoWithUserFindCommand(
                UserCommand.UserFindCommand.builder()
                        .userToken(user.getUserToken())
                        .build());

        Assertions.assertEquals(user.getUserName(), foundUser.getUserName());
    }
}