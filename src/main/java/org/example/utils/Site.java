package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class Site {

    public static Document open(String URL){
        try {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            WebDriver driver = new ChromeDriver(options);
            driver.get(URL);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(".product-node"))));
            Document page = Jsoup.parse(driver.getPageSource());
            driver.quit();
            return page;
        }catch (Exception e){
            log.error("Ощибка доступа к сайту: {}", e);
            return null;
        }
    }
}
