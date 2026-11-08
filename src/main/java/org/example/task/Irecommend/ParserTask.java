package org.example.task.Irecommend;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Review;
import org.example.service.ReviewService;
import org.example.utils.ErrorHandler;
import org.example.utils.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ParserTask implements Runnable {
    private final String URL;
    private final ReviewService reviewService;
    private final AtomicInteger counter;
    private final String productName;

    public ParserTask(String productName, String URL, ReviewService reviewService, AtomicInteger counter){
        this.URL =  URL;
        this.reviewService = reviewService;
        this.counter = counter;
        this.productName = productName;
    }

    @Override
    public void run(){
        try {

            Document page = Site.open(URL);
            Elements reviews = page.select(".list-comments").select(".item");
            for(Element review : reviews){
                String URL = "https://irecommend.ru" + review.selectFirst(".reviewTextSnippet").attr("href");
                if(reviewService.getReviewByURL(URL) == null){
                    String reviewText = review.selectFirst(".reviewTitle").text();
                    int rating = review.select(".star").select(".on").size();
                    reviewService.saveReview(new Review(productName, URL, reviewText, rating));
                    counter.getAndIncrement();
                }
            }
        }catch (Exception e){
            new ErrorHandler().uncaughtException(Thread.currentThread(), e);
        }
    }
}
