package org.example.service;

import org.example.domain.Review;
import org.example.repo.ReviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo){
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getReviews() {
        return reviewRepo.findAll();
    }

    public Review getById(Long id){
        var reviewOptional = reviewRepo.findById(id);
        return reviewOptional.orElseThrow(() -> new RuntimeException("отзыв не найден" + id));
    }

    public Review saveReview(Review review){
        return reviewRepo.save(review);
    }
}
