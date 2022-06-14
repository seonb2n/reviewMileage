package com.example.reviewmileage.domain.review.service;

import com.example.reviewmileage.domain.review.Review;

public interface ReviewStore {

    Review store(Review review);

    Review registerReview(Review initReview);

    void delete(String reviewToken);
}
