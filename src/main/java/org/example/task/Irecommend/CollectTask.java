package org.example.task.Irecommend;


import lombok.extern.slf4j.Slf4j;
import org.example.service.ReviewService;
import org.example.utils.DaemonLog;
import org.example.utils.ErrorHandler;
import org.example.utils.Site;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CollectTask implements Runnable {
    private final ReviewService reviewService;
    private final String URL;
    private final ThreadPoolExecutor executorService;
    private final AtomicInteger counter;

    public CollectTask(String URL, ReviewService reviewService){
        this.URL = URL;
        this.reviewService = reviewService;
        this.executorService  = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        this.counter = new AtomicInteger(0);
    }

    @Override
    public void run(){
        new DaemonLog(executorService, counter).start();
        try{
            Document page = Site.open(URL);
            String productName = page.selectFirst(".product-node").selectFirst(".fn").text();
            List<Element> pager = page.select(".pager");
            int lastPageNumber;
            if(!pager.isEmpty()){
                lastPageNumber = Integer.parseInt(page.selectFirst(".pager-last").selectFirst(".active").text());
            } else {
                lastPageNumber = 1;
            }

            for(int i = 0; i < lastPageNumber; i++){
                    if(i == 0){
                        executorService.submit(new ParserTask(productName ,URL, reviewService, counter));
                    }else {
                        executorService.submit( new ParserTask(productName, URL + "?page=" + i, reviewService, counter));
                    }
            }
        }catch (Exception e){
            new ErrorHandler().uncaughtException(Thread.currentThread(), e);
        }finally {
            executorService.shutdown();
        }
    }
}
