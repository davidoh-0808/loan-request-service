//package com.neo.config.batch;
//
//import com.neo.common.vo.ConsultInfoVO;
//import com.neo.util.UtilOtp;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.batch.MyBatisBatchItemWriter;
//import org.mybatis.spring.batch.MyBatisCursorItemReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.List;
//
//@Configuration
//@EnableScheduling
//@EnableBatchProcessing
//public class ConInfoBatchConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(ConInfoBatchConfig.class);
//
//    @Autowired
//    private JobExplorer jobExplorer;
//    @Autowired
//    private JobRepository jobRepository;
//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private SqlSessionFactoryBean sqlSessionFactoryBean;
//
//
//
//    //매일 낮 12시 실행
//    @Scheduled(cron = "* 12 * * ?")
//    public void runJob1() {
//        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//
//        try {
//             jobLauncher.run( job1(), jobParameters );
//        } catch (Exception e) {
//            logger.error( e.getMessage() );
//        }
//
//    }
//
//    @Bean
//    public JobExecutionListener listener() {
//        return new BatchListener();
//    }
//
//    @Bean
//    public MyBatisCursorItemReader< List<ConsultInfoVO> > reader() throws Exception {
//        MyBatisCursorItemReader reader = new MyBatisCursorItemReader<ConsultInfoVO>();
//
////        reader.setSqlSessionFactory( databaseConfig.getSqlSessionFactoryBean().getObject() );
//        reader.setSqlSessionFactory( sqlSessionFactoryBean.getObject() );
//
//        reader.setQueryId("com.neo.mappers.ConsultInfoMapper.consultInfoList");
//
//        return reader;
//    }
//
//    @Bean
//    public ConInfoBatchProcessor processor() {
//        return new ConInfoBatchProcessor();
//    }
//
//    @Bean
//    public MyBatisBatchItemWriter< List<ConsultInfoVO> > writer() throws Exception {
//        MyBatisBatchItemWriter writer = new MyBatisBatchItemWriter<ConsultInfoVO>();
//
//        writer.setSqlSessionFactory( sqlSessionFactoryBean.getObject() );
//
//        writer.setStatementId("com.neo.mappers.ConsultInfoMapper.consultInfoUpdate");
//
//        return writer;
//    }
//
//    @Bean
//    public Job job1() {
//        //toDO:
//        return this.jobBuilderFactory.get("Job1").listener("Job1").incrementer())
//    }
//
//}
