package com.example.shcrawler.domain.crawl.source.repository;

import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlingSourceRepository extends JpaRepository<CrawlingSource, Long> {

}
