package com.example.reviewmileage.application;

import com.example.reviewmileage.application.place.PlaceFacade;
import com.example.reviewmileage.application.review.ReviewFacade;
import com.example.reviewmileage.application.user.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FacadeTest {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ReviewFacade reviewFacade;

    @Autowired
    private PlaceFacade placeFacade;

}
