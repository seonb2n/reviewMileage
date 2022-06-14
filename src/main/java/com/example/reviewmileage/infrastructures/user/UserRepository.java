package com.example.reviewmileage.infrastructures.user;

import com.example.reviewmileage.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{

    Optional<User> findUserByUserToken(String userToken);

}
