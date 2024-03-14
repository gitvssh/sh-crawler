package com.example.shcrawler.util.parser;

import com.example.shcrawler.domain.board.Board;
import com.example.shcrawler.domain.crawl.data.CrawlData;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumorUnivParser implements CrawlingParser<CrawlData, Board> {

    private String title;
    private String content;
    private long viewCount;
    private String writer;
    private String writerId;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;

    @Override
    public void parse(CrawlData crawlData) {
        String crawlContent = crawlData.getCrawlContent();
        title = getTitle(crawlContent);
        content = getContent(crawlContent);
        viewCount = 0;
        writer = getWriter(crawlContent);
        writerId = getWriterId(crawlContent);
        likeCount = getLikeCount(crawlContent);
        dislikeCount = getDisLikeCount(crawlContent);
        commentCount = getCommentCount(crawlContent);
    }

    @Override
    public Board getResult() {
        return Board.builder()
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .writer(writer)
                .writerId(writerId)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .commentCount(commentCount)
                .build();
    }

    private String getTitle(String content) {
        String regex = "<title>(.*?)</title>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no title";
        }
    }

    private String getContent(String content) {
        String regex = "<div class=\"body_editor\".?>(.*?)</div>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no title";
        }
    }

    // 수정 필요
    private String getViewCount(String content) {
        String regex = "<span class=\"etc\">(.*?)</span>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no writer";
        }
    }


    private String getWriter(String content) {
        String regex = "<span class=\"hu_nick_txt\">(.*?)</span>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no writer";
        }
    }

    private String getWriterId(String content) {
        String regex = "<title>(.*?)</title>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "no writerId";
        }
    }

    private int getLikeCount(String content) {
        String regex = "<span id=\"ok_div\">(\\d+)</span>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            String numberString = matcher.group(1);
            return Integer.parseInt(numberString);
        } else {
            return -1;
        }
    }

    private int getDisLikeCount(String content) {
        String regex = "<span id=\"not_ok_span\">(\\d+)</span>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            String numberString = matcher.group(1);
            return Integer.parseInt(numberString);
        } else {
            return -1;
        }
    }

    private int getCommentCount(String content) {
        String regex = "<span class=\"comment_num\">(\\d+)</span>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            String numberString = matcher.group(1);
            return Integer.parseInt(numberString);
        } else {
            return -1;
        }
    }

    /**
     * 리스트 페이지를 파싱해 url 리스트를 반환
     *
     * @return url 리스트
     */
    @Override
    public List<String> parseList() {
        return new ArrayList<>();
    }
}
