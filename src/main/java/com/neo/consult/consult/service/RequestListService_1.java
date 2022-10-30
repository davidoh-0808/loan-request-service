package com.neo.consult.consult.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.ConsultInfoVO;

public interface RequestListService {

	public List<ConsultInfoVO> consultInfoList(ConsultInfoVO paramVO) throws Exception;
	public ConsultInfoVO consultStatsCount(ConsultInfoVO paramVO) throws Exception;
	public ConsultInfoVO consultInfoDetail(ConsultInfoVO paramVO) throws Exception;
	
	
	public JSONObject requestInsert(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject requestUpdate(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject requestDelete(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject assignConsultantOnList(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject assignConsultantOnDetail(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;




}
