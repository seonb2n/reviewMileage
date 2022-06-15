package com.example.reviewmileage.domain.review.photos;

import java.util.List;

public interface PhotoService {

    PhotoInfo.Main registerPhoto(PhotoCommand.PhotoRegisterCommand registerCommand);

    List<Photo> findPhotoWithPhotoToken(List<String> photoToken);
}
