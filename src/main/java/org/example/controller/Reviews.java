package org.example.controller;

import org.example.domain.Review;
import org.example.service.ReviewProcessingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Review")
public class Reviews {
    private final ReviewProcessingService reviewProcessingService;

    public Reviews(ReviewProcessingService reviewProcessingService){
        this.reviewProcessingService = reviewProcessingService;
    }

    @GetMapping("/filtred")
    public List<Review> Filtred(@RequestParam String ProductName, @RequestParam Integer rating){
        return reviewProcessingService.GetReviews(ProductName, rating);
    }

}
