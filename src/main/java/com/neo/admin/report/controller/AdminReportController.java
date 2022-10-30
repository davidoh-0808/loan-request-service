package com.neo.admin.report.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.report.service.AdminReportService;
import com.neo.common.view.PagingView;
import com.neo.common.view.UrlMappingInfo;
import com.neo.common.vo.ReportVO;
import com.neo.util.UtilCommon;
import com.neo.util.UtilDate;

@Controller
public class AdminReportController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="adminReportService")
	private AdminReportService adminReportService;
	
	@Value("${spring.mvc.format.date}")
	private String DATE_FORMATT;
	/**
	 * 통계 목록
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@GetMapping("/admin/report/reportDeviceList")
	public ModelAndView reportDeviceList(@ModelAttribute ReportVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/report/reportDeviceList");
		int totCount = 0;
		List<ReportVO> resultList = new ArrayList<ReportVO>();
		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		
		if (UtilCommon.isEmpty(paramVO.getSearchStartDt())) 
		{
			paramVO.setSearchStartDt(UtilDate.calcDate(UtilDate.getDateFormatt(DATE_FORMATT), DATE_FORMATT, "MONTH", -1));
			paramVO.setSearchEndDt(UtilDate.getDateFormatt(DATE_FORMATT));
		}
		// E:파람 초기값

		totCount = adminReportService.reportListCount(paramVO);
		
		if(totCount > 0) {
			resultList = adminReportService.reportDeviceList(paramVO);
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCount", totCount);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	@GetMapping("/admin/report/reportPageList")
	public ModelAndView reportPageList(@ModelAttribute ReportVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/report/reportPageList");
		int totCount = 0;
		List<ReportVO> resultList = new ArrayList<ReportVO>();
		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		
		if (UtilCommon.isEmpty(paramVO.getSearchStartDt())) 
		{
			paramVO.setSearchStartDt(UtilDate.calcDate(UtilDate.getDateFormatt(DATE_FORMATT), DATE_FORMATT, "MONTH", -1));
			paramVO.setSearchEndDt(UtilDate.getDateFormatt(DATE_FORMATT));
		}
		// E:파람 초기값

		totCount = adminReportService.reportListCount(paramVO);
		
		if(totCount > 0) {
			resultList = adminReportService.reportPageList(paramVO);
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		for(ReportVO item : resultList) {
			for(String key : UrlMappingInfo.urlInfo.keySet()) {
				if(key.equals(item.getREPO_PATH())) {
					item.setREPO_PATH_NAME(UrlMappingInfo.urlInfo.get(key));
				}
			}
		}
		mav.addObject("totCount", totCount);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		return mav;
	}

}
