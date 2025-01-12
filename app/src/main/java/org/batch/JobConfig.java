package org.batch;

import org.batch.facade.DummySalesDataFacade;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    private DummySalesDataFacade dummysalesDataFacade;

    public JobConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job sampleJob() {
        return new JobBuilder("sampleJob", jobRepository)
                .incrementer(new RunIdIncrementer()) // 毎回異なるパラメータを付与
                .start(sampleStep())
                .build();
    }

    @Bean
    public Step sampleStep() {
        return new StepBuilder("sampleStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    dummysalesDataFacade.createDummySalesData();
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}