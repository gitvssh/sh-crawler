package com.example.shcrawler.domain.crawl.source.repository;

import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawlingSourceRepository extends JpaRepository<CrawlingSource, Long> {

    public Optional<List<CrawlingSource>> findAll(String name);
}
