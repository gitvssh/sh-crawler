package com.example.shcrawler.batch.listener;

import com.example.shcrawler.domain.crawl.CrawlingService;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class CrawlingJobListener implements JobExecutionListener {

    private final CrawlingService crawlingService;

    public CrawlingJobListener(CrawlingService crawlingService) {
        this.crawlingService = crawlingService;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 크롤링 리스트 조회
        List<CrawlingSource> crawlingSourceList = crawlingService.getCrawlingSourceList();
        if (crawlingSourceList.isEmpty()) {
            throw new RuntimeException("크롤링 대상이 없습니다.");
        } else {
            JobExecutionListener.super.beforeJob(jobExecution);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //수행 이력 등록
        JobExecutionListener.super.afterJob(jobExecution);
    }
}
