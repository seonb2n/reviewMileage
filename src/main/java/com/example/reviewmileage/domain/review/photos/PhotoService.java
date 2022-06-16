package com.example.reviewmileage.domain.review.photos;

import com.example.reviewmileage.domain.review.Review;

import java.util.List;

public interface PhotoService {

    PhotoInfo.Main registerPhoto(PhotoCommand.PhotoRegisterCommand registerCommand);

    List<Photo> findPhotoWithPhotoToken(List<String> photoToken);

    void deletePhotoWithReview(Review review);
}
