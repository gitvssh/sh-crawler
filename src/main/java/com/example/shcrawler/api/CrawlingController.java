package com.example.shcrawler.api;


import com.example.shcrawler.domain.crawl.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("/crawling/")
    public String crawling() {
        return "getTest";
    }

    @PostMapping("/crawling/")
    public String crawlingPost() {
        return "PostTest";
    }

}
