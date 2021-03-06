package com.example.reviewmileage.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserCommand {

    @Getter
    @Builder
    @ToString
    public static class UserRegisterCommand {
        private String userName;

        public User toEntity() {
            return User.builder()
                    .userName(userName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class UserFindCommand {
        private String userToken;
    }

}
