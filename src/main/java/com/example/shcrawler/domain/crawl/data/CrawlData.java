package com.example.shcrawler.domain.crawl.data;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sh_data_collection") // 실제 몽고 DB 컬렉션 이름
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrawlData {

    @Id
    private String id;

    private String rawUrl;
    private String rawContent;

    @Getter
    private String crawlContent;
    private String status;

}