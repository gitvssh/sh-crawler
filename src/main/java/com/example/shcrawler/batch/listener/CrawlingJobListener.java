package com.example.shcrawler.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CrawlingJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // 크롤링 리스트 조회
        // 리스트 항목 없으면 크롤링
        if (true) {
            JobExecutionListener.super.beforeJob(jobExecution);
        } else {
            // 수행 이력 등록
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //수행 이력 등록
        JobExecutionListener.super.afterJob(jobExecution);
    }
}
