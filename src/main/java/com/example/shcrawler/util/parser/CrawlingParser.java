package com.example.shcrawler.util.parser;

import java.util.List;

public interface CrawlingParser<T, O> {

    void parse(T t);

    O getResult();

    List<String> parseList();
}
