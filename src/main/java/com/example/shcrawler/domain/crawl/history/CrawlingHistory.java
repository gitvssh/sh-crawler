package com.example.shcrawler.domain.crawl.history;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "crawling_history")
public class CrawlingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crawling_history_id")
    private Long id;

    @Column(name = "crawling_count", nullable = false ,columnDefinition = "bigint")
    private long crawlingCount;

    @Column(name = "status", columnDefinition = "varchar(30)")
    private String status;

}
