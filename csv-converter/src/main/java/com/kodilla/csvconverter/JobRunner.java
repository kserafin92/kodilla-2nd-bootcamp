package com.kodilla.csvconverter;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job changePriceJob;

    public JobRunner(JobLauncher jobLauncher, Job changePriceJob) {
        this.jobLauncher = jobLauncher;
        this.changePriceJob = changePriceJob;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(changePriceJob, new JobParameters());
    }
}
