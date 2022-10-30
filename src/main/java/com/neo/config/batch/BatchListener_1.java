//package com.neo.config.batch;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.batch.core.listener.JobExecutionListenerSupport;
//
//import javax.batch.runtime.BatchStatus;
//
//
//public class BatchListener extends JobExecutionListenerSupport {
//    Logger logger = LoggerFactory.getLogger(BatchListener.class);
//
//    @Override
//    public void beforeJob(JobExecution jobExecution) {
//        if ( jobExecution.getStatus().equals(BatchStatus.STARTED) ) {
//            logger.info("************ BATCH JOB STARTED 배치 태스크 시작 ************");
//        }
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        if ( jobExecution.getStatus().equals(BatchStatus.COMPLETED) ) {
//            logger.info("************ BATCH JOB COMPLETED 배치 태스크 완료 ************");
//        }
//    }
//}
