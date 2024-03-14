package com.example.shcrawler.domain.crawl.data.repository;

import com.example.shcrawler.domain.crawl.data.CrawlData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrawlDataRepository extends MongoRepository<CrawlData, Long> {

}
