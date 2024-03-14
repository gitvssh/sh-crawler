package com.example.shcrawler.domain.crawl;

import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CrawlingService {

    public List<CrawlingSource> getCrawlingSourceList();

    public void saveCrawlData(String url, String crawlData);

    void saveCrawlingHistory(CrawlingSource crawlingSource);
}
