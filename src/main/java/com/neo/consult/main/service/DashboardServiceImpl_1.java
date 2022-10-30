package com.neo.consult.main.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.common.service.CommonService;
import com.neo.common.vo.DashboardVO;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.mappers.ConsultMemberMapper;
import com.neo.mappers.DashboardMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Transactional
@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "dashboardMapper")
    private DashboardMapper dashboardMapper;
    @Resource(name = "consultMemberMapper")
    private ConsultMemberMapper consultMemberMapper;
    @Resource(name = "commonService")
    private CommonService commonService;


    // board & chart
    @Override
    public DashboardVO getDashboard(DashboardVO paramVO) throws Exception {
    	return dashboardMapper.getDashboard(paramVO);
    }
    
    // chard
    @Override
    public List<DashboardVO> getDashboardChart(DashboardVO paramVO) throws Exception {
    	return dashboardMapper.getDashboardChart(paramVO);
    }
    
    //USER_CNT
    @Override
    public DashboardVO getUserCnt(DashboardVO paramVO) throws Exception {
    	return dashboardMapper.getUserCnt(paramVO);
    }
    
    //Total USER_CNT
    @Override
    public DashboardVO getUserTotalCnt(DashboardVO paramVO) throws Exception {
    	return dashboardMapper.getUserTotalCnt(paramVO);
    }
    
    
    
    
    

}
