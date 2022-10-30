package com.neo.mappers;

import com.neo.common.vo.DashboardVO;
import com.neo.config.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("dashboardMapper")
public interface DashboardMapper {
	
	public DashboardVO getDashboard(DashboardVO paramVO) throws Exception;
	
	public List<DashboardVO> getDashboardChart(DashboardVO paramVO) throws Exception;
	
	public DashboardVO getUserCnt(DashboardVO paramVO) throws Exception;
	
	public DashboardVO getUserTotalCnt(DashboardVO paramVO) throws Exception;
	
}
