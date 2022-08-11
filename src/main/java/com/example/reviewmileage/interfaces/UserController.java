package com.example.reviewmileage.interfaces;

import com.example.reviewmileage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteUser(@RequestBody String userToken) {
        userService.deleteUser(userToken);
        return ResponseEntity.ok("사용자가 삭제됐습니다.");
    }
}
