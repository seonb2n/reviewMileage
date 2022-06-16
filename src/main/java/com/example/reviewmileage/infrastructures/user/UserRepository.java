package com.example.reviewmileage.infrastructures.user;

import com.example.reviewmileage.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{

    @Query("select u from User u left join fetch u.reviewList where u.userToken= :userToken")
    Optional<User> findUserByUserToken(String userToken);

}
