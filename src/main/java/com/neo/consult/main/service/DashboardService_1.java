package com.neo.consult.main.service;


import com.neo.common.vo.DashboardVO;

import java.util.List;

public interface DashboardService {

	public DashboardVO getDashboard(DashboardVO paramVO) throws Exception;

	public List<DashboardVO> getDashboardChart(DashboardVO paramVO) throws Exception;	
	
	public DashboardVO getUserCnt(DashboardVO paramVO) throws Exception;
	
	public DashboardVO getUserTotalCnt(DashboardVO paramVO) throws Exception;
	

}
