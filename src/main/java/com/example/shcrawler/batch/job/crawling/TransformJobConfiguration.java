package com.example.shcrawler.batch.job.crawling;

import com.example.shcrawler.batch.listener.CrawlingJobListener;
import com.example.shcrawler.domain.board.Board;
import com.example.shcrawler.domain.crawl.CrawlingService;
import com.example.shcrawler.domain.crawl.data.CrawlData;
import com.example.shcrawler.util.parser.CrawlingParser;
import com.example.shcrawler.util.parser.HumorUnivParser;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoCursorItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class TransformJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final CrawlingService crawlingService;

    @Bean
    public Job transfromJob() {
        return new JobBuilder("transfromJob", jobRepository)
                .listener(new CrawlingJobListener(crawlingService))
                .incrementer(new RunIdIncrementer())
                .start(transformation())
                .build();
    }

    @Bean
    @JobScope
    public Step transformation() {
        return new StepBuilder("transformation", jobRepository)
                .allowStartIfComplete(true)
                .chunk(10, transactionManager)
                .reader(getCrawlData())
                .processor(getCrawlDataProcessor())
                .writer(getCrawlDataWriter())
                .build();
    }

    /**
     * 크롤링 데이터를 읽어옴, crawl은 크롤링이 완료되고 변환되지 않은 데이터
     *
     * @return ItemReader
     */
    @Bean
    public ItemReader<CrawlData> getCrawlData() {

        MongoCursorItemReader<CrawlData> reader = new MongoCursorItemReader<>();
        reader.setQuery("{status: 'crawl'}");
        reader.setTargetType(CrawlData.class);
        reader.setCollection("sh_data_collection");

        return reader;
    }

    /**
     * 크롤링 데이터를 Board로 변환
     *
     * @return ItemProcessor
     */
    @Bean
    public ItemProcessor<? super Object,Board> getCrawlDataProcessor() {
        return crawlData -> {
            CrawlingParser humorUnivParser = new HumorUnivParser();
            humorUnivParser.parse(crawlData);
            return (Board) humorUnivParser.getResult();
        };
    }

    /**
     * JPA Writer를 사용하여 db에 저장
     *
     * @return
     */
    @Bean
    public ItemWriter<? super Object> getCrawlDataWriter() {
        return new JpaItemWriterBuilder<>()
                .build();
    }
}
