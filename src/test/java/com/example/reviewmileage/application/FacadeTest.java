package com.example.reviewmileage.application;

import com.example.reviewmileage.application.place.PlaceFacade;
import com.example.reviewmileage.application.review.ReviewFacade;
import com.example.reviewmileage.application.user.UserFacade;
import com.example.reviewmileage.domain.place.PlaceCommand;
import com.example.reviewmileage.domain.place.PlaceInfo;
import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.ReviewCommand;
import com.example.reviewmileage.domain.review.photos.PhotoCommand;
import com.example.reviewmileage.domain.review.photos.PhotoInfo;
import com.example.reviewmileage.domain.review.photos.PhotoService;
import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


/**
 * 1. 새로운 리뷰 추가 테스트
 * 2. 기존의 리뷰 변경 테스트
 * 3. 리뷰 삭제 테스트
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FacadeTest {

    Logger log = (Logger) LoggerFactory.getLogger(FacadeTest.class);

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ReviewFacade reviewFacade;

    @Autowired
    private PlaceFacade placeFacade;

    @Autowired
    private PhotoService photoService;

    private static UserInfo.Main user;
    private static PlaceInfo.Main place;
    private static PhotoInfo.Main photo1;
    private static PhotoInfo.Main photo2;

    private static final String reviewToken = "review_58-dc5f-4878-9381-ebb7b2667772";
    private static final String photoToken1 = "photo_4e-a531-46de-88d0-ff0ed70c0bb82";
    private static final String photoToken2 = "photo_e2-851d-4a50-bb07-9cc15cbdc332";


    @BeforeAll
    void beforeAll() {
        UserCommand.UserRegisterCommand userRegisterCommand = UserCommand.UserRegisterCommand.builder()
                .userName("test-user")
                .build();
        user = userFacade.registerUser(userRegisterCommand);

        PlaceCommand.PlaceRegisterCommand placeRegisterCommand = PlaceCommand.PlaceRegisterCommand.builder()
                .placeName("test-place")
                .build();
        place = placeFacade.registerPlace(placeRegisterCommand);

        photo1 = photoService.registerPhoto(PhotoCommand.PhotoRegisterCommand.builder()
                .data("photo1")
                .photoToken(photoToken1)
                .build());

        photo2 = photoService.registerPhoto(PhotoCommand.PhotoRegisterCommand.builder()
                .data("photo2")
                .photoToken(photoToken2)
                .build());
    }

    //1. 리뷰 등록 및 마일리지 반영 테스트
    @Test
    public void addReviewTest() {

        List<String> photoTokenList = new ArrayList<>();
        photoTokenList.add(photoToken1);
        photoTokenList.add(photoToken2);

        var reviewRegisterCommand = ReviewCommand.ReviewRegisterCommand.builder()
                .user(userFacade.findUserWithUserToken(user.getUserToken()))
                .place(placeFacade.findPlaceWithPlaceToken(place.getPlaceToken()))
                .reviewToken(reviewToken)
                .photoList(photoService.findPhotoWithPhotoToken(photoTokenList))
                .content("good").build();

        var review = reviewFacade.registerReview(reviewRegisterCommand);
        log.info(review.toString());
        Assertions.assertEquals(3, review.getMileagePoint());
        User updateUser = userFacade.findUserWithUserToken(user.getUserToken());
        Assertions.assertEquals(3, updateUser.getUserMileagePoint());
    }

    //1.1 두 번째 리뷰 추가시 해당 사용자의 마일리지 테스트
    @Test
    public void addNewReviewTest() {
        var user2 = userFacade.registerUser(UserCommand.UserRegisterCommand.builder()
                .userName("test-user2")
                .build());

        var reviewRegisterCommand = ReviewCommand.ReviewRegisterCommand.builder()
                .user(userFacade.findUserWithUserToken(user2.getUserToken()))
                .place(placeFacade.findPlaceWithPlaceToken(place.getPlaceToken()))
                .reviewToken(reviewToken+"2")
                .photoList(new ArrayList<>())
                .content("good").build();

        var review = reviewFacade.registerReview(reviewRegisterCommand);
        log.info(String.valueOf(review.getMileagePoint()));

        var foundUser = userFacade.findUserWithUserToken(user2.getUserToken());
        Assertions.assertEquals(1, foundUser.getUserMileagePoint());
    }

    //2. 기존의 리뷰 변경 시 마일리지 수정 및 마일리지 변경 히스토리 테스트
    @Test
    public void modReviewTest() {
        var reviewUpdateCommand = ReviewCommand.ReviewUpdateCommand.builder()
                .reviewToken(reviewToken)
                .content("mod-content")
                .photoList(new ArrayList<>())
                .build();
        var review = reviewFacade.modReview(reviewUpdateCommand);
        Assertions.assertEquals(2, review.getMileagePoint());
    }

    //3. 기존 리뷰 삭제 테스트
    @Test
    public void deleteReviewTest() {
        var reviewDeleteCommand = ReviewCommand.ReviewDeleteCommand.builder()
                .reviewToken(reviewToken)
                .build();
        reviewFacade.deleteReview(reviewDeleteCommand);
    }
}
