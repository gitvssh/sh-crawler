package com.example.shcrawler.api;


import com.example.shcrawler.domain.crawl.CrawlingService;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import com.example.shcrawler.util.WebDriverUtil;
import java.util.List;
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
        List<CrawlingSource> crawlingSourceList = crawlingService.getCrawlingSourceList();
        return crawlingSourceList.size()+"개의 크롤링 대상이 있습니다.";
    }

    @PostMapping("/crawling/")
    public String crawlingPost() {
        List<CrawlingSource> crawlingSourceList = crawlingService.getCrawlingSourceList();
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        for (CrawlingSource crawlingSource : crawlingSourceList) {
            String url = crawlingSource.getUrl();
            String scraping = webDriverUtil.scraping(url);
            crawlingService.saveCrawlData(crawlingSource.getUrl(), scraping);
            crawlingService.saveCrawlingHistory(crawlingSource);
        }
        return "PostTest";
    }

}
