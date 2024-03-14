package com.example.shcrawler.domain.crawl.source;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "crawling_source")
public class CrawlingSource {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crawling_source_id")
    private Long id;

    @Column(name = "crawling_url", unique = true, nullable = false ,columnDefinition = "varchar(500)")
    private String crawlingUrl;

    @Column(name = "site_name", columnDefinition = "varchar(300)")
    private String siteName;

    @Column(name = "crawling_object", columnDefinition = "varchar(1000)")
    private String crawlingObject;

    @Column(name = "crawling_type", columnDefinition = "varchar(100)")
    private String crawlingType;

}
