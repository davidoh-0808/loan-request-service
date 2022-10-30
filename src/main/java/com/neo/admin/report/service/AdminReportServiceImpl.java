package com.neo.admin.report.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.ReportVO;
import com.neo.mappers.CommonMapper;
import com.neo.mappers.ReportMapper;

@Service("adminReportService")
@Transactional
public class AdminReportServiceImpl implements AdminReportService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	@Resource(name="reportMapper")
	private ReportMapper reportMapper;
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Override
	public int reportListCount(ReportVO paramVO) throws Exception {
		return reportMapper.reportListCount(paramVO);
	}

	@Override
	public List<ReportVO> reportDeviceList(ReportVO paramVO) throws Exception {
		List<ReportVO> resultList = new ArrayList<>();
		resultList = reportMapper.reportDeviceList(paramVO);
		return resultList;
	}

	@Override
	public List<ReportVO> reportPageList(ReportVO paramVO) throws Exception {
		List<ReportVO> resultList = new ArrayList<>();
		resultList = reportMapper.reportPageList(paramVO);
		return resultList;
	}

}
