package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.task.Irecommend.CollectTask;
import org.example.utils.ErrorHandler;
import org.example.utils.Site;
import org.jsoup.nodes.Document;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Service
public class MonitoringReviewService {
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    private final Map<String, ScheduledFuture<?>> monitoringTasks = new ConcurrentHashMap<>();

    private final ReviewService reviewService;

    public MonitoringReviewService(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    public void startMonitoring(String URL){
        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                Document page = Site.open(URL);
                String productName = page.selectFirst(".product-node").selectFirst(".fn").text();
                int reviewCount = Integer.parseInt(page.selectFirst(".total-votes").selectFirst(".count").text());
                boolean makeNewCollect = reviewService.getReviewsByName(productName).size() != reviewCount;
                if (makeNewCollect){
                    new Thread(new CollectTask(URL, reviewService)).start();
                    log.info("Новые отзывы по отслеживанию {} в процессе добавления", URL);
                }else {
                    log.info("Новых отзывов по отслеживанию {} не было найдено", URL);
                }
            }catch (Exception e){
                new ErrorHandler().uncaughtException(Thread.currentThread(), e);
            }
        }, 0, 5, TimeUnit.MINUTES);
        monitoringTasks.put(URL, future);
    }

    public void stopMonitoring(String URL){
        ScheduledFuture<?> future = monitoringTasks.remove(URL);

        if(future != null){
            future.cancel(false);
        }
    }
}
