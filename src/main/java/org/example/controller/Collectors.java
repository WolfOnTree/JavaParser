package org.example.controller;


import org.example.service.ReviewService;
import org.example.task.Irecommend.CollectTask;
import org.example.utils.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Collect")
public class Collectors {
    private final ReviewService reviewService;

    public Collectors(ReviewService reviewService){
        this.reviewService = reviewService;
    }


    @GetMapping("/Irecommend")
    public String collectIrecommend(@RequestParam String URL){
        if (!Validator.isValidURL(URL)) {
            return "Некорректная ссылка";
        }

        new Thread(new CollectTask(URL, reviewService)).start();

        return "Парсинг запущен";
    }
}
