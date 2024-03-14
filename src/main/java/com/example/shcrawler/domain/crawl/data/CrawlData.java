package com.example.shcrawler.domain.crawl.data;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sh_data_collection") // 실제 몽고 DB 컬렉션 이름
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrawlData {

    @Id
    private String id;

    private String rawUrl;
    private String rawContent;

    private String crawlContent;
}