package com.example.reviewmileage.common.util;

import com.example.reviewmileage.application.place.PlaceFacade;
import com.example.reviewmileage.application.review.ReviewFacade;
import com.example.reviewmileage.application.user.UserFacade;
import com.example.reviewmileage.domain.place.Place;
import com.example.reviewmileage.domain.place.PlaceCommand;
import com.example.reviewmileage.domain.place.PlaceInfo;
import com.example.reviewmileage.domain.review.photos.PhotoCommand;
import com.example.reviewmileage.domain.review.photos.PhotoInfo;
import com.example.reviewmileage.domain.review.photos.PhotoService;
import com.example.reviewmileage.domain.user.User;
import com.example.reviewmileage.domain.user.UserCommand;
import com.example.reviewmileage.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

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

    private static final String userToken = "user_e0ef2-92b7-4817-a5f3-0c575361f745";
    private static final String placeToken = "place_f1c-5acb-4efb-a1af-eddada31b00f";
    private static final String reviewToken = "review_58-dc5f-4878-9381-ebb7b2667772";
    private static final String photoToken1 = "photo_4e-a531-46de-88d0-ff0ed70c0bb82";
    private static final String photoToken2 = "photo_e2-851d-4a50-bb07-9cc15cbdc332";

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserCommand.UserRegisterCommand userRegisterCommand = UserCommand.UserRegisterCommand.builder()
                .userName("test-user")
                .build();
        user = userFacade.registerUser(userRegisterCommand);
        User userWithUserToken = userFacade.findUserWithUserToken(user.getUserToken());
        userWithUserToken.updateUserToken(userToken);

        PlaceCommand.PlaceRegisterCommand placeRegisterCommand = PlaceCommand.PlaceRegisterCommand.builder()
                .placeName("test-place")
                .build();
        place = placeFacade.registerPlace(placeRegisterCommand);

        Place placeWithPlaceToken = placeFacade.findPlaceWithPlaceToken(place.getPlaceToken());
        placeWithPlaceToken.updatePlaceToken(placeToken);

        photo1 = photoService.registerPhoto(PhotoCommand.PhotoRegisterCommand.builder()
                .data("photo1")
                .photoToken(photoToken1)
                .build());

        photo2 = photoService.registerPhoto(PhotoCommand.PhotoRegisterCommand.builder()
                .data("photo2")
                .photoToken(photoToken2)
                .build());


    }
}

