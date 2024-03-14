package com.example.shcrawler.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class WebDriverUtil {
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    @Value("${webdriver.path}")
    private static String webDriverPath;

    //setProperty 메소드를 통해 프로그램명과 경로 받기
//base_url에 스크래핑 할 브라우저 url 작성
    public void scraping(String url) {
        // url 유효성 검사
        System.setProperty(WEB_DRIVER_ID, webDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        try {
            Thread.sleep(5000);

            String pageSource = driver.getPageSource();

            log.info(pageSource);

        } catch (InterruptedException e) {
            log.warn("InterruptedException occurred");
            Thread.currentThread().interrupt();
        } finally {
            driver.quit();
        }
    }
}