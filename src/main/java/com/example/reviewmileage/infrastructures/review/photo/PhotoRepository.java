package com.example.reviewmileage.infrastructures.review.photo;

import com.example.reviewmileage.domain.review.photos.Photo;
import com.example.reviewmileage.domain.review.photos.PhotoCommand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findPhotoByPhotoToken(String photoToken);

}
