package org.example.service;

import org.example.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewProcessingService {
    private final ReviewService reviewService;

    public ReviewProcessingService(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    public List<Review> GetReviews(String Name, Integer rating){
        List<Review> Reviews = reviewService.getReviewsByName(Name);
        return Reviews.parallelStream()
                .filter(review -> review.getRating() == rating)
                .toList();
    }
}
