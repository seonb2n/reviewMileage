package com.example.reviewmileage.infrastructures.review.photo;

import com.example.reviewmileage.domain.review.photos.Photo;
import com.example.reviewmileage.domain.review.photos.PhotoCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
