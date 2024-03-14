package com.example.shcrawler.domain.board;

import com.example.shcrawler.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(500)")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "view_count", columnDefinition = "bigint default 0")
    private long viewCount;

    @Column(name = "writer", columnDefinition = "varchar(255)")
    private String writer;

    @Column(name = "writer_id", columnDefinition = "varchar(255)")
    private String writerId;

    @Column(name = "like_count", columnDefinition = "bigint default 0")
    private int likeCount;

    @Column(name = "dislike_count", columnDefinition = "bigint default 0")
    private int dislikeCount;

    @Column(name = "comment_count", columnDefinition = "bigint default 0")
    private int commentCount;
}
