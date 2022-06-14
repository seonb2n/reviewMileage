package com.example.reviewmileage.domain.review.service;

import com.example.reviewmileage.domain.review.Review;

public interface ReviewReader {

    Review getReviewWithReviewToken(String reviewToken);

}
