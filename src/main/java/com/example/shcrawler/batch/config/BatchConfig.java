package com.example.shcrawler.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(BatchProperties.class)
@RequiredArgsConstructor
public class BatchConfig {

    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final JobRepository jobRepository;
    private final BatchProperties batchProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "spring.batch.job", name = "enabled", havingValue = "true", matchIfMissing = true)
    public JobLauncherApplicationRunner jobLauncherApplicationRunner() {
        JobLauncherApplicationRunner jobLauncherApplicationRunner = new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
        String name = batchProperties.getJob().getName();
        if (name != null) {
            jobLauncherApplicationRunner.setJobName(name);
        }
        return jobLauncherApplicationRunner;
    }
}
