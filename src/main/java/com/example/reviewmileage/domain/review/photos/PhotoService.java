package com.example.reviewmileage.domain.review.photos;

public interface PhotoService {

    PhotoInfo.Main registerPhoto(PhotoCommand.PhotoRegisterCommand registerCommand);

}
