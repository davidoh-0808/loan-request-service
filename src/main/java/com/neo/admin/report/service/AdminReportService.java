package com.neo.admin.report.service;

import java.util.List;

import com.neo.common.vo.ReportVO;

public interface AdminReportService {
	
	/**
	 * 총 건수 계산
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int reportListCount(ReportVO paramVO) throws Exception;
	
	
	/**
	 * 기기별 통계 리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<ReportVO> reportDeviceList(ReportVO paramVO) throws Exception;
	
	/**
	 * 페이지뷰 통계 리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<ReportVO> reportPageList(ReportVO paramVO) throws Exception;
}
