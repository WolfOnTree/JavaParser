package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DaemonLog {
    private final ThreadPoolExecutor executor;
    private final AtomicInteger counter;

    public DaemonLog(ThreadPoolExecutor executor, AtomicInteger counter){
        this.executor = executor;
        this.counter = counter;
    }

    public void start(){
        Thread daemonLog = new Thread(() -> {
            while (!executor.isTerminated()){
                log.info("Всего задач: {}, Завершено: {}, Активно: {}, В очереди: {}",
                        executor.getTaskCount(),
                        executor.getCompletedTaskCount(),
                        executor.getActiveCount(),
                        executor.getQueue().size());
                log.info("Всего отзывов обработано: {}", counter.get());
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
            }
            log.info("Обработка завершена. Всего отзывов добавлено: {}", counter.get());
        });

        daemonLog.setDaemon(true);
        daemonLog.start();
    }
}
