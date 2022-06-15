package com.example.reviewmileage.domain.review.photos;

import com.example.reviewmileage.common.exception.TokenNotFoundException;
import com.example.reviewmileage.infrastructures.review.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService{

    private final PhotoRepository photoRepository;

    @Override
    public PhotoInfo.Main registerPhoto(PhotoCommand.PhotoRegisterCommand registerCommand) {
        Photo initPhoto = registerCommand.toEntity();
        Photo photo = photoRepository.save(initPhoto);
        return new PhotoInfo.Main(photo);
    }

    @Override
    public List<Photo> findPhotoWithPhotoToken(List<String> photoTokenList) {
        List<Photo> photoList = new ArrayList<>();
        photoTokenList.forEach(photoToken -> {
            var photo = photoRepository.findPhotoByPhotoToken(photoToken).orElseThrow(TokenNotFoundException::new);
            photoList.add(photo);
        });
        return photoList;
    }
}
