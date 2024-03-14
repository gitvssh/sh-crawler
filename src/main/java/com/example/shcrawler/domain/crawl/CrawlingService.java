package com.example.shcrawler.domain.crawl;

import com.example.shcrawler.domain.crawl.history.CrawlingHistory;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CrawlingService {

    public List<CrawlingSource> getCrawlingSourceList();

    public void crawlingPost();

    // 이후 구현
    void saveCrawlingHistory(CrawlingHistory crawlingHistory);
}
