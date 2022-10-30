//package com.neo.config.batch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableScheduling
//public class BatchScheduler {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job job;
//
//    //매일 낮 12시 실행
//    @Scheduled(cron = "* 12 * * ?")
//    public void batchScheduler() {
//        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//
//
//    }
//
//}
