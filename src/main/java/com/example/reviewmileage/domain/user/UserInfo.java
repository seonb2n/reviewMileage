package com.example.reviewmileage.domain.user;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class UserInfo {

    @Getter
    @ToString
    public static class Main {
        private final String userToken;
        private final String userName;
        private final int userMileagePoint;

        public Main(User user) {
            this.userToken = user.getUserToken();
            this.userName = user.getUserName();
            this.userMileagePoint = user.getUserMileagePoint();
        }
    }

}
