package com.example.shcrawler.domain.crawl.history.repository;

import com.example.shcrawler.domain.crawl.history.CrawlingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlingHistoryRepository extends JpaRepository<CrawlingHistory, Long> {

}
