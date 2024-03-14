package com.example.shcrawler.domain.crawl.history;

import com.example.shcrawler.domain.common.BaseEntity;
import com.example.shcrawler.domain.crawl.source.CrawlingSource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "crawling_history")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrawlingHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crawling_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crawling_source_id")
    private CrawlingSource crawlingSource;

    @Column(name = "status", columnDefinition = "varchar(30)")
    private String status;

}
