package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.example.service.ReviewService;

@Slf4j
public class CollectTask implements Runnable{
    private final String URL;
    private final ReviewService reviewService;
    public CollectTask(String URL, ReviewService reviewService){
        this.URL = URL;
        this.reviewService = reviewService;
    }


    @Override
    public void run() {
        try {
            log.info("Поток '{}' стартовал. Приоритет: {}",
                    Thread.currentThread().getName(),
                    Thread.currentThread().getPriority());

            reviewService.getReviews();
        } catch (Exception e){
            log.error("Ошибка в потоке " + Thread.currentThread().getName() + "errorMessage " + e.getMessage());
        }
    }
}

