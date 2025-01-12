package org.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job sampleJob;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    public void run() {
        try {
            System.out.println("Launching the Job...");
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // 毎回異なるパラメータ
                    .toJobParameters();
            jobLauncher.run(sampleJob, jobParameters);
            System.out.println("Job completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
