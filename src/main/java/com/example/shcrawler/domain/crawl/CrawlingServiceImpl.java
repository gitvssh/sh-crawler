package com.example.shcrawler.domain.crawl;

import com.example.shcrawler.domain.crawl.history.CrawlingHistory;
import com.example.shcrawler.domain.crawl.history.repository.CrawlingHistoryRepository;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import com.example.shcrawler.domain.crawl.source.repository.CrawlingSourceRepository;
import com.example.shcrawler.util.WebDriverUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrawlingServiceImpl implements CrawlingService {

    private final CrawlingSourceRepository crawlingRepository;
    private final CrawlingHistoryRepository crawlingHistoryRepository;

    private final MongoTemplate mongoTemplate;

    // queryDSL 적용 후 Optional 처리 할 것
    @Override
    public List<CrawlingSource> getCrawlingSourceList() {
        return crawlingRepository.findAll();
    }

    // 이후 구현
    @Override
    public void crawlingPost() {
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        webDriverUtil.scraping("https://www.naver.com");
    }

    // 이후 구현
    @Override
    public void saveCrawlingHistory(CrawlingHistory crawlingHistory) {
        crawlingHistoryRepository.save(crawlingHistory);
    }
}
