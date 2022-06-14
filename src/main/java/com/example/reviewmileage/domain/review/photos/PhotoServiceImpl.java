package com.example.reviewmileage.domain.review.photos;

import com.example.reviewmileage.infrastructures.review.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
