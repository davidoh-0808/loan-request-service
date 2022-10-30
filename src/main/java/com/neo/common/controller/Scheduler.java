/**
 * 
 */
package com.neo.common.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.neo.mappers.ConsultInfoMapper;
import com.neo.mappers.ConsultMemberMapper;

/**
 * @author seowon
 *
 */
@SpringBootApplication
@EnableScheduling
public class Scheduler {

	@Resource(name = "consultMemberMapper")
    private ConsultMemberMapper consultMemberMapper;
	
	@Resource(name = "consultInfoMapper")
	private ConsultInfoMapper consultInfoMapper;
	
	/**
	 * @param args
	 */
    //@Scheduled(cron = "10 * * * * *") //매분 10초마다
    @Scheduled(cron = "0 0 2 * * *") //매일 새벽 2시마다
    public void run() throws Exception{
        // TODO
        System.out.println("현재 시간은 " + new Date());
        
        try {
        	//로그인 1(31일)개월 이상 장기미사용 처리
        	consultMemberMapper.memberDeactivate();
        	
        	//상담신청 3(90일)개월 지나면 정보 삭제....
        	//조건 확인할 것 
        	consultInfoMapper.consultInfoDeleteBatch();
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
       
    }

}
