package com.example.shcrawler.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.shcrawler.util.WebDriverUtil;
import org.junit.jupiter.api.Test;

class WebDriverUtilTest {

    @Test
    void getWebDriver() {
        // Given
        WebDriverUtil webDriverUtil = new WebDriverUtil();
        // When
        webDriverUtil.scraping("https://www.naver.com");
        // Then

        assertThat(webDriverUtil).isNotNull();
    }

}