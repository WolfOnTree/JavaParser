package org.example.controller;

import org.example.domain.Review;
import org.example.service.MonitoringReviewService;
import org.example.service.ReviewProcessingService;
import org.example.utils.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Schedule")
public class Schedule {
    private final MonitoringReviewService monitoringReviewService;

    public Schedule(MonitoringReviewService monitoringReviewService){
        this.monitoringReviewService = monitoringReviewService;
    }

    @GetMapping("/start")
    public String Start(@RequestParam String URL){
        if (!Validator.isValidURL(URL)) {
            return "Некорректная ссылка";
        }

        monitoringReviewService.startMonitoring(URL);
        return "Мониторинг запущен";
    }

    @GetMapping("/stop")
    public String Stop(@RequestParam String URL){
        if (!Validator.isValidURL(URL)) {
            return "Некорректная ссылка";
        }

        monitoringReviewService.stopMonitoring(URL);
        return "Мониториг остановлен";
    }
}
