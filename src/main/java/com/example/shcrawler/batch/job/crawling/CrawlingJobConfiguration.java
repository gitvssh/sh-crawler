package com.example.shcrawler.batch.job.crawling;

import com.example.shcrawler.batch.listener.CrawlingJobListener;
import com.example.shcrawler.domain.crawl.CrawlingService;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import com.example.shcrawler.util.WebDriverUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class CrawlingJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final CrawlingService crawlingService;

    @Bean
    public Job crawlingJob() {
        return new JobBuilder("crawlingJob", jobRepository)
                .listener(new CrawlingJobListener(crawlingService))
                .incrementer(new RunIdIncrementer())
                // 항목 데이터 조회, 크롤링 객체 생성
                .start(getCrawlingSource())
                // 크롤링 객체 리스트로 크롤링
//                .next(getCrawlData())
//                .next(saveCrawlData())
                // DB 적재
                .build();
    }

    @Bean
    @JobScope
    public Step getCrawlingSource() {
        return new StepBuilder("getCrawlingSource", jobRepository)
                .allowStartIfComplete(true)
                .tasklet(getCrawlingSourceTasklet(), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet getCrawlingSourceTasklet() {
        List<CrawlingSource> crawlingSourceList = crawlingService.getCrawlingSourceList();
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                WebDriverUtil webDriverUtil = new WebDriverUtil();
                for (CrawlingSource crawlingSource : crawlingSourceList) {
                    // 크롤링
                    String url = crawlingSource.getUrl();
                    String scraping = webDriverUtil.scraping(url);

                    // 크롤링 결과 저장
                    crawlingService.saveCrawlData(url, scraping);

                    // 크롤링 이력 저장
                    crawlingService.saveCrawlingHistory(crawlingSource);
                }
                return null;
            }
        };
    }
}
