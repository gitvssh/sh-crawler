package com.example.shcrawler.domain.crawl;

import com.example.shcrawler.domain.crawl.data.CrawlData;
import com.example.shcrawler.domain.crawl.data.repository.CrawlDataRepository;
import com.example.shcrawler.domain.crawl.history.CrawlingHistory;
import com.example.shcrawler.domain.crawl.history.repository.CrawlingHistoryRepository;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import com.example.shcrawler.domain.crawl.source.repository.CrawlingSourceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrawlingServiceImpl implements CrawlingService {

    private final CrawlingSourceRepository crawlingRepository;
    private final CrawlingHistoryRepository crawlingHistoryRepository;
    private final CrawlDataRepository crawlDataRepository;
    private final MongoTemplate mongoTemplate;

    // queryDSL 적용 후 Optional 처리 할 것

    /**
     * 크롤링 대상 리스트 조회
     * <p>
     * 데이터베이스에서 크롤링 대상 리스트를 조회한다.
     *
     * @return
     */
    @Override
    public List<CrawlingSource> getCrawlingSourceList() {
        return crawlingRepository.findAll();
    }

    /**
     * 크롤링 데이터 저장
     *
     * @param url
     * @param crawlData
     */
    @Override
    public void saveCrawlData(String url, String crawlData) {
        CrawlData crawlingData = CrawlData.builder()
                .rawContent(crawlData)
                .rawUrl(url)
                .crawlContent("test"+crawlData) // 추후 크롤링 데이터 처리 로직 추가
                .build();
        mongoTemplate.save(crawlingData);
        // 디버깅용 중복 저장
        CrawlData crawlingData2 = CrawlData.builder()
                .rawContent(crawlData)
                .rawUrl(url)
                .crawlContent("test2"+crawlData) // 추후 크롤링 데이터 처리 로직 추가
                .build();
//        crawlDataRepository.save(crawlingData);
        mongoTemplate.insert(crawlingData2);
        CrawlData crawlingData3 = CrawlData.builder()
                .rawContent(crawlData)
                .rawUrl(url)
                .crawlContent("test3"+crawlData) // 추후 크롤링 데이터 처리 로직 추가
                .build();
        mongoTemplate.save(crawlingData3);
        CrawlData crawlingData4 = CrawlData.builder()
                .rawContent(crawlData)
                .rawUrl(url)
                .crawlContent("test4"+crawlData) // 추후 크롤링 데이터 처리 로직 추가
                .build();
//        crawlDataRepository.save(crawlingData);
        mongoTemplate.insert(crawlingData4);
    }

    /**
     * 크롤링 성공 이력 저장
     *
     * @param crawlingSource
     */
    @Override
    public void saveCrawlingHistory(CrawlingSource crawlingSource) {
        CrawlingHistory success = CrawlingHistory.builder()
                .crawlingSource(crawlingSource)
                .status("SUCCESS")
                .build();

        crawlingHistoryRepository.save(success);
    }
}
